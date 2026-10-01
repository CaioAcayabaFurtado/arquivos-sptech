"""Analisa o dataset simulado de logs da Aula 06.

Gera um relatorio Markdown com achados rastreaveis ao XLSX original.
"""

from __future__ import annotations

import argparse
import re
from collections import defaultdict
from datetime import datetime, timedelta
from pathlib import Path

from openpyxl import load_workbook


AUTH_SHEET = "Logs_Autenticacao"
TRAFFIC_SHEET = "Trafico_Rede"
SIEM_SHEET = "Alertas_SIEM"


def read_records(workbook, sheet_name: str) -> list[dict[str, object]]:
    sheet = workbook[sheet_name]
    rows = list(sheet.values)
    headers = [str(value).strip() if value is not None else "" for value in rows[0]]
    records = []
    for row in rows[1:]:
        record = dict(zip(headers, row))
        event_id = str(record.get("ID") or "").strip()
        if re.fullmatch(r"(?:AUT|TRF|ALR)\d+", event_id):
            records.append(record)
    return records


def event_datetime(record: dict[str, object]) -> datetime:
    date_value = str(record["Data"])
    time_value = str(record["Hora"])
    return datetime.strptime(f"{date_value} {time_value}", "%d/%m/%Y %H:%M")


def is_external_ip(value: object) -> bool:
    if value is None:
        return False
    return not str(value).startswith("10.")


def format_bytes(value: object) -> str:
    number = float(value or 0)
    if number >= 1_000_000_000:
        return f"{number / 1_000_000_000:.1f} GB"
    if number >= 1_000_000:
        return f"{number / 1_000_000:.1f} MB"
    return f"{number:.0f} bytes"


def auth_findings(auth_records: list[dict[str, object]]) -> list[dict[str, object]]:
    findings = []
    failures_by_user_ip: defaultdict[tuple[object, object], list[dict[str, object]]] = defaultdict(list)

    for record in auth_records:
        timestamp = event_datetime(record)
        user = record.get("Usuário")
        ip = record.get("IP Origem")
        hour = timestamp.hour
        if record.get("Resultado") == "Falha":
            failures_by_user_ip[(user, ip)].append(record)
        if record.get("País") in {"RU", "US", "NL"}:
            findings.append({
                "id": f"AUTH-{record['ID']}",
                "title": "Autenticacao originada de pais externo",
                "timestamp": timestamp,
                "risk": "alto" if record.get("País") == "RU" else "medio",
                "evidence": (
                    f"{record['ID']}: {user} acessou {record.get('Sistema')} "
                    f"a partir de {record.get('IP Origem')} ({record.get('País')})."
                ),
                "response": "UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada.",
            })
        if hour < 6 or hour >= 22:
            findings.append({
                "id": f"AUTH-{record['ID']}",
                "title": "Login fora da janela operacional",
                "timestamp": timestamp,
                "risk": "alto" if record.get("País") in {"RU", "NL"} else "medio",
                "evidence": (
                    f"{record['ID']}: {user} acessou {record.get('Sistema')} "
                    f"as {record.get('Hora')} usando {record.get('Dispositivo')}."
                ),
                "response": "UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana.",
            })

    for (user, ip), records in failures_by_user_ip.items():
        records.sort(key=event_datetime)
        for start_index in range(len(records)):
            window = [
                record for record in records[start_index:]
                if event_datetime(record) - event_datetime(records[start_index]) <= timedelta(minutes=10)
            ]
            if len(window) >= 3:
                first = window[0]
                findings.append({
                    "id": f"AUTH-BRUTE-{first['ID']}",
                    "title": "Multiplas falhas de autenticacao em janela curta",
                    "timestamp": event_datetime(first),
                    "risk": "critico" if len(window) >= 5 and is_external_ip(ip) else "alto",
                    "evidence": (
                        f"{len(window)} falhas para {user} a partir de {ip} entre "
                        f"{window[0].get('Hora')} e {window[-1].get('Hora')}."
                    ),
                    "response": "UEBA: elevar score de risco; SOAR: bloquear IP, exigir reset de senha e escalar para analista.",
                })
                break
    return findings


def traffic_findings(traffic_records: list[dict[str, object]]) -> list[dict[str, object]]:
    findings = []
    for record in traffic_records:
        timestamp = event_datetime(record)
        source = record.get("IP Origem")
        destination = record.get("IP Destino")
        bytes_transferred = int(record.get("Bytes Transferidos") or 0)
        external_destination = is_external_ip(destination)
        if record.get("Direção") == "Saída" and external_destination and bytes_transferred >= 100_000_000:
            risk = "critico" if bytes_transferred >= 1_000_000_000 else "alto"
            findings.append({
                "id": f"TRAFFIC-{record['ID']}",
                "title": "Transferencia de grande volume para IP externo",
                "timestamp": timestamp,
                "risk": risk,
                "evidence": (
                    f"{record['ID']}: {source} enviou {format_bytes(bytes_transferred)} "
                    f"para {destination} via {record.get('Protocolo')} as {record.get('Hora')}."
                ),
                "response": "UEBA: comparar volume e destino com o baseline da entidade; SOAR: limitar fluxo, preservar evidencias e isolar o endpoint.",
            })
        if record.get("Protocolo") == "SSH" and external_destination:
            findings.append({
                "id": f"TRAFFIC-{record['ID']}-SSH",
                "title": "Conexao SSH de servidor para IP externo",
                "timestamp": timestamp,
                "risk": "alto",
                "evidence": (
                    f"{record['ID']}: conexao SSH de {source} para {destination}, "
                    f"com {format_bytes(bytes_transferred)} transferidos."
                ),
                "response": "UEBA: verificar se a conta de servico possui essa finalidade; SOAR: bloquear destino e escalar para revisao humana.",
            })
    return findings


def siem_findings(siem_records: list[dict[str, object]]) -> list[dict[str, object]]:
    findings = []
    for record in siem_records:
        severity = str(record.get("Severidade") or "").lower()
        status = str(record.get("Status") or "").lower()
        if severity in {"alta", "critica"} or status == "ignorado":
            risk = "critico" if severity == "critica" or status == "ignorado" else "alto"
            findings.append({
                "id": f"SIEM-{record['ID']}",
                "title": str(record.get("Tipo de Alerta")),
                "timestamp": event_datetime(record),
                "risk": risk,
                "evidence": f"{record['ID']}: {record.get('Descrição')} Status={record.get('Status')}; acao={record.get('Ação Tomada')}.",
                "response": "SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana.",
            })
    return findings


def render_report(source: Path, auth_records, traffic_records, siem_records, findings) -> str:
    findings.sort(key=lambda finding: (finding["timestamp"], finding["id"]))
    counts = defaultdict(int)
    for finding in findings:
        counts[finding["risk"]] += 1
    lines = [
        "# Analise de Logs - Aula 06",
        "",
        f"Fonte: `{source.name}`",
        "",
        "## Escopo e metodologia",
        "",
        "Foram analisados os registros de autenticacao, trafego de rede e alertas do SIEM. As regras usam horario, origem externa, repeticao de falhas, volume de dados, protocolo e severidade. Os achados sao indicios priorizados, nao uma confirmacao juridica de comprometimento.",
        "",
        "## Inventario",
        "",
        f"- Autenticacao: {len(auth_records)} registros",
        f"- Trafego de rede: {len(traffic_records)} registros",
        f"- Alertas SIEM: {len(siem_records)} registros",
        f"- Achados priorizados: {len(findings)}",
        f"- Distribuicao de risco: {dict(counts)}",
        "",
        "## Achados",
        "",
        "| ID | Data/hora | Risco | Padrao e evidencia | Resposta UEBA/SOAR |",
        "|---|---|---|---|---|",
    ]
    for finding in findings:
        timestamp = finding["timestamp"].strftime("%d/%m/%Y %H:%M")
        evidence = str(finding["evidence"]).replace("|", "\\|")
        response = str(finding["response"]).replace("|", "\\|")
        lines.append(f"| {finding['id']} | {timestamp} | **{finding['risk'].upper()}** | **{finding['title']}**: {evidence} | {response} |")
    lines.extend([
        "",
        "## Limitacoes",
        "",
        "- O recorte possui 72 horas e poucos registros; o baseline comportamental e inicial.",
        "- A correlacao entre autenticacao e trafego depende principalmente de IP e proximidade temporal.",
        "- Acoes de bloqueio, isolamento, revogacao de tokens e reset de credenciais devem seguir aprovacao e procedimento interno.",
        "",
        "## Proximos passos",
        "",
        "1. Validar os achados com o responsavel pelo ambiente e identificar falsos positivos autorizados.",
        "2. Reabrir o alerta critico ignorado e correlaciona-lo com as seis falhas VPN e o trafego externo.",
        "3. Transformar os achados confirmados em playbooks SOAR auditaveis.",
        "4. Ampliar o historico para calibrar o baseline UEBA e medir falsos positivos, MTTD e MTTR.",
        "",
    ])
    return "\n".join(lines)


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--input", type=Path, default=Path("Dataset_Logs_Aula06_alunos.xlsx"))
    parser.add_argument("--output", type=Path, default=Path("relatorio_analise_aula06.md"))
    args = parser.parse_args()

    workbook = load_workbook(args.input, read_only=True, data_only=True)
    auth_records = read_records(workbook, AUTH_SHEET)
    traffic_records = read_records(workbook, TRAFFIC_SHEET)
    siem_records = read_records(workbook, SIEM_SHEET)
    findings = auth_findings(auth_records) + traffic_findings(traffic_records) + siem_findings(siem_records)
    args.output.write_text(render_report(args.input, auth_records, traffic_records, siem_records, findings), encoding="utf-8")
    print(f"Relatorio gerado: {args.output}")
    print(f"Achados: {len(findings)}")


if __name__ == "__main__":
    main()