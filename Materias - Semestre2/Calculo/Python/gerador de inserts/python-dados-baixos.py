from datetime import datetime, timedelta
import random

def generate_sql_inserts_low_usage():
    # Valores base para recursos de uma máquina de baixo uso
    base_values = {
        'usoCPU': (5, 20),  # Valores mais baixos para simular baixa carga
        'usoRAM': (5, 25),
        'usoTotal': (5, 20),
        'velocidadeDownload': (10, 50),
        'velocidadeUpload': (10, 40),
        'erroPacotesEntrada': (0, 2),
        'erroPacotesSaida': (0, 2),
        'descartePacotesEntrada': (0, 1),
        'descartePacotesSaida': (0, 1),
        'megabytesRecebidos': (10, 100),
        'megabytesEnviados': (5, 80),
        'pacotesEnviados': (50, 200),
        'pacotesRecebidos': (60, 250)
    }

    # Define a data de referência (hoje) como ponto fixo
    today = datetime(2024, 12, 3)

    # Calcula a data inicial de cada semana
    weeks = []
    for i in range(5):
        week_start = today - timedelta(days=(7 * (i + 1)))
        week_end = week_start + timedelta(days=6)
        week_label = f"SEMANA {i + 1} - {week_start.strftime('%d/%m/%Y')} até {week_end.strftime('%d/%m/%Y')}"
        weeks.append((week_start, week_label))

    # Reverte as semanas para que a mais recente venha primeiro
    weeks.reverse()

    with open('inserts_captura_ultimas_5_semanas_baixo_uso.txt', 'w', encoding='utf-8') as file:
        file.write("-- Inserindo dados para uma máquina com baixo uso\n")

        for week_start, week_label in weeks:
            file.write(f"\n-- {week_label}\n")

            for day in range(7):
                current_date = week_start + timedelta(days=day)
                file.write(f"\n-- Dia {current_date.strftime('%Y-%m-%d')}\n")

                for hour in range(24):
                    # Variação de uso por horário reduzida
                    time_of_day_factor = random.uniform(0.9, 1.1)  # Pouca variação
                    weekday_factor = 1.0  # Sem ajuste significativo para dias úteis ou fins de semana
                    stress_factor = 1.0  # Sem picos

                    file.write(f"\n-- Hora {hour:02d}:00\n")
                    file.write("INSERT INTO ServGuard.Captura (fkMaquinaRecurso, registro, dthCriacao, isAlerta) VALUES\n")

                    inserts = []
                    for resource, (min_val, max_val) in base_values.items():
                        # Gera valor base e ajusta com múltiplos fatores
                        variation = random.randint(min_val, max_val)
                        variation = int(variation * time_of_day_factor * weekday_factor * stress_factor)

                        # Garantir consistência nos valores baixos
                        if resource in ['usoCPU', 'usoRAM']:
                            variation = min(variation, 30)  # Limite inferior mais restrito

                        # Menor chance de alertas em baixo uso
                        is_alert = 1 if resource in ['usoCPU', 'usoRAM'] and variation > 25 else 0

                        timestamp = f"{current_date.strftime('%Y-%m-%d')} {hour:02d}:00:00"

                        insert = f"""    ((SELECT idMaquinaRecurso FROM ServGuard.MaquinaRecurso 
        WHERE fkMaquina = 3 AND fkRecurso = (SELECT idRecurso FROM ServGuard.Recurso WHERE nome = '{resource}')), 
        {variation}, '{timestamp}', {is_alert})"""
                        inserts.append(insert)

                    file.write(',\n'.join(inserts) + ';\n')

if __name__ == "__main__":
    generate_sql_inserts_low_usage()
    print("Arquivo 'inserts_captura_ultimas_5_semanas_baixo_uso.txt' gerado com sucesso!")
