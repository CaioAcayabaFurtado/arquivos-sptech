# Analise de Logs - Aula 06

Fonte: `Dataset_Logs_Aula06_alunos.xlsx`

## Inventario

- Autenticacao: 55 registros
- Trafego de rede: 45 registros
- Alertas SIEM: 25 registros
- Achados priorizados: 34
- Distribuicao de risco: {'medio': 13, 'alto': 17, 'critico': 4}

## Achados

| ID | Data/hora | Risco | Padrao e evidencia | Resposta UEBA/SOAR |
|---|---|---|---|---|
| AUTH-AUT003 | 14/09/2026 08:35 | **MEDIO** | **Autenticacao originada de pais externo**: AUT003: mariana.prado acessou E-mail a partir de 189.45.12.50 (US). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT011 | 14/09/2026 22:10 | **MEDIO** | **Login fora da janela operacional**: AUT011: carlos.oliveira acessou VPN as 22:10 usando Notebook. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT012 | 15/09/2026 03:12 | **ALTO** | **Autenticacao originada de pais externo**: AUT012: mariana.prado acessou ERP a partir de 185.220.101.44 (RU). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT012 | 15/09/2026 03:12 | **ALTO** | **Login fora da janela operacional**: AUT012: mariana.prado acessou ERP as 03:12 usando Notebook. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| SIEM-ALR005 | 15/09/2026 03:15 | **ALTO** | **Login fora do horário**: ALR005: Login de mariana.prado no ERP às 03:12 a partir de IP externo (RU) Status=Em análise; acao=Investigação manual. | SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana. |
| AUTH-AUT023 | 15/09/2026 17:40 | **MEDIO** | **Autenticacao originada de pais externo**: AUT023: carlos.oliveira acessou Backup a partir de 189.45.12.94 (US). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| TRAFFIC-TRF020 | 16/09/2026 02:03 | **CRITICO** | **Transferencia de grande volume para IP externo**: TRF020: 10.0.70.5 enviou 2.4 GB para 185.220.101.44 via HTTPS as 02:03. | UEBA: comparar volume e destino com o baseline da entidade; SOAR: limitar fluxo, preservar evidencias e isolar o endpoint. |
| TRAFFIC-TRF021 | 16/09/2026 02:04 | **CRITICO** | **Transferencia de grande volume para IP externo**: TRF021: 10.0.70.5 enviou 1.8 GB para 185.220.101.44 via HTTPS as 02:04. | UEBA: comparar volume e destino com o baseline da entidade; SOAR: limitar fluxo, preservar evidencias e isolar o endpoint. |
| SIEM-ALR010 | 16/09/2026 02:05 | **ALTO** | **Transferência de dados anômala**: ALR010: Transferência de 2,4 GB do servidor TMS para IP externo suspeito Status=Novo; acao=Nenhuma. | SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana. |
| AUTH-AUT024 | 16/09/2026 02:47 | **MEDIO** | **Autenticacao originada de pais externo**: AUT024: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT024 | 16/09/2026 02:47 | **ALTO** | **Login fora da janela operacional**: AUT024: joao.silva acessou VPN as 02:47 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-BRUTE-AUT024 | 16/09/2026 02:47 | **CRITICO** | **Multiplas falhas de autenticacao em janela curta**: 6 falhas para joao.silva a partir de 45.155.204.77 entre 02:47 e 02:56. | UEBA: elevar score de risco; SOAR: bloquear IP, exigir reset de senha e escalar para analista. |
| AUTH-AUT025 | 16/09/2026 02:49 | **MEDIO** | **Autenticacao originada de pais externo**: AUT025: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT025 | 16/09/2026 02:49 | **ALTO** | **Login fora da janela operacional**: AUT025: joao.silva acessou VPN as 02:49 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| SIEM-ALR011 | 16/09/2026 02:50 | **CRITICO** | **Múltiplas falhas de autenticação**: ALR011: 6 falhas consecutivas de login VPN para usuário joao.silva em 9 minutos a partir de IP externo Status=Ignorado; acao=Nenhuma. | SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana. |
| AUTH-AUT026 | 16/09/2026 02:51 | **MEDIO** | **Autenticacao originada de pais externo**: AUT026: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT026 | 16/09/2026 02:51 | **ALTO** | **Login fora da janela operacional**: AUT026: joao.silva acessou VPN as 02:51 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT027 | 16/09/2026 02:53 | **MEDIO** | **Autenticacao originada de pais externo**: AUT027: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT027 | 16/09/2026 02:53 | **ALTO** | **Login fora da janela operacional**: AUT027: joao.silva acessou VPN as 02:53 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT028 | 16/09/2026 02:55 | **MEDIO** | **Autenticacao originada de pais externo**: AUT028: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT028 | 16/09/2026 02:55 | **ALTO** | **Login fora da janela operacional**: AUT028: joao.silva acessou VPN as 02:55 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT029 | 16/09/2026 02:56 | **MEDIO** | **Autenticacao originada de pais externo**: AUT029: joao.silva acessou VPN a partir de 45.155.204.77 (NL). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT029 | 16/09/2026 02:56 | **ALTO** | **Login fora da janela operacional**: AUT029: joao.silva acessou VPN as 02:56 usando Desktop. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT030 | 16/09/2026 04:20 | **MEDIO** | **Login fora da janela operacional**: AUT030: sistema.wms acessou WMS as 04:20 usando Servidor. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| AUTH-AUT036 | 17/09/2026 02:05 | **MEDIO** | **Login fora da janela operacional**: AUT036: sistema.tms acessou TMS as 02:05 usando Servidor. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| TRAFFIC-TRF029 | 17/09/2026 02:06 | **ALTO** | **Transferencia de grande volume para IP externo**: TRF029: 10.0.70.5 enviou 320.0 MB para 45.155.204.77 via HTTPS as 02:06. | UEBA: comparar volume e destino com o baseline da entidade; SOAR: limitar fluxo, preservar evidencias e isolar o endpoint. |
| TRAFFIC-TRF030 | 17/09/2026 03:40 | **ALTO** | **Transferencia de grande volume para IP externo**: TRF030: 10.0.70.9 enviou 850.0 MB para 45.155.204.77 via SSH as 03:40. | UEBA: comparar volume e destino com o baseline da entidade; SOAR: limitar fluxo, preservar evidencias e isolar o endpoint. |
| TRAFFIC-TRF030-SSH | 17/09/2026 03:40 | **ALTO** | **Conexao SSH de servidor para IP externo**: TRF030: conexao SSH de 10.0.70.9 para 45.155.204.77, com 850.0 MB transferidos. | UEBA: verificar se a conta de servico possui essa finalidade; SOAR: bloquear destino e escalar para revisao humana. |
| SIEM-ALR017 | 17/09/2026 03:45 | **ALTO** | **Conexão a IP suspeito**: ALR017: Conexão SSH do servidor WMS para IP externo com volume alto Status=Novo; acao=Nenhuma. | SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana. |
| AUTH-AUT039 | 17/09/2026 10:10 | **MEDIO** | **Autenticacao originada de pais externo**: AUT039: carlos.oliveira acessou TMS a partir de 191.33.8.31 (US). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT044 | 18/09/2026 02:30 | **ALTO** | **Autenticacao originada de pais externo**: AUT044: mariana.prado acessou VPN a partir de 185.220.101.44 (RU). | UEBA: validar localizacao e dispositivo; SOAR: exigir MFA e revogar a sessao se nao autorizada. |
| AUTH-AUT044 | 18/09/2026 02:30 | **ALTO** | **Login fora da janela operacional**: AUT044: mariana.prado acessou VPN as 02:30 usando Notebook. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
| SIEM-ALR021 | 18/09/2026 02:35 | **ALTO** | **Login fora do horário**: ALR021: Segundo login de mariana.prado a partir de IP russo suspeito Status=Novo; acao=Nenhuma. | SOAR: reabrir ou escalar o caso, enriquecer com autenticacao e trafego correlatos e exigir validacao humana. |
| AUTH-AUT055 | 18/09/2026 23:58 | **MEDIO** | **Login fora da janela operacional**: AUT055: roberto.lima acessou ERP as 23:58 usando Mobile. | UEBA: comparar com o horario habitual; SOAR: abrir incidente e bloquear a sessao sob aprovacao humana. |
