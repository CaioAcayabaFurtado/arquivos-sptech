from datetime import datetime, timedelta
import random

def generate_sql_inserts():
    # Valores base para cada recurso com variação
    base_values = {
        'usoCPU': (20, 85),  # Ajuste para simular menor uso constante
        'usoRAM': (15, 80),
        'usoTotal': (10, 70),
        'velocidadeDownload': (40, 120),
        'velocidadeUpload': (30, 110),
        'erroPacotesEntrada': (0, 5),
        'erroPacotesSaida': (0, 5),
        'descartePacotesEntrada': (0, 3),
        'descartePacotesSaida': (0, 3),
        'megabytesRecebidos': (50, 600),
        'megabytesEnviados': (20, 500),
        'pacotesEnviados': (200, 900),
        'pacotesRecebidos': (300, 1000)
    }

    # Define a data de referência (hoje) como ponto fixo
    today = datetime(2024, 12, 5)

    # Calcula a data inicial de cada semana
    weeks = []
    for i in range(5):
        week_start = today - timedelta(days=(7 * (i + 1)))
        week_end = week_start + timedelta(days=6)
        week_label = f"SEMANA {i + 1} - {week_start.strftime('%d/%m/%Y')} até {week_end.strftime('%d/%m/%Y')}"
        weeks.append((week_start, week_label))

    # Reverte as semanas para que a mais recente venha primeiro
    weeks.reverse()

    with open('inserts_captura_ultimas_5_semanas.txt', 'w', encoding='utf-8') as file:
        file.write("-- Inserindo dados para as últimas cinco semanas com variações realistas\n")

        for week_start, week_label in weeks:
            file.write(f"\n-- {week_label}\n")

            for day in range(7):
                current_date = week_start + timedelta(days=day)
                file.write(f"\n-- Dia {current_date.strftime('%Y-%m-%d')}\n")

                for hour in range(24):
                    # Variação de uso por horário
                    time_of_day_factor = 1.0
                    if 8 <= hour < 18:  # Horário comercial
                        time_of_day_factor = random.uniform(1.2, 1.5)
                    elif 0 <= hour < 6:  # Horário de menor uso
                        time_of_day_factor = random.uniform(0.6, 0.9)

                    # Redução no fim de semana
                    weekday_factor = 1.0 if current_date.weekday() < 5 else random.uniform(0.5, 0.8)

                    # Pico ocasional
                    stress_factor = random.uniform(1.0, 2.0) if random.random() < 0.1 else 1.0  # 10% chance de pico

                    file.write(f"\n-- Hora {hour:02d}:00\n")
                    file.write("INSERT INTO ServGuard.Captura (fkMaquinaRecurso, registro, dthCriacao, isAlerta) VALUES\n")

                    inserts = []
                    for resource, (min_val, max_val) in base_values.items():
                        # Gera valor base e ajusta com múltiplos fatores
                        variation = random.randint(min_val, max_val)
                        variation = int(variation * time_of_day_factor * weekday_factor * stress_factor)

                        # Limita valores para manter realismo
                        if resource in ['usoCPU', 'usoRAM']:
                            variation = min(variation, 95)  # Nunca ultrapassa 95%
                        elif resource in ['velocidadeDownload', 'velocidadeUpload']:
                            variation = min(variation, 130)

                        # Correlaciona erros de pacotes com alto uso de CPU ou RAM
                        if resource in ['erroPacotesEntrada', 'erroPacotesSaida', 'descartePacotesEntrada', 'descartePacotesSaida']:
                            correlation = random.randint(0, 5) if variation > 80 else random.randint(0, 2)
                            variation = max(variation, correlation)

                        # Ajusta probabilidade de alerta
                        is_alert = 1 if resource in ['usoCPU', 'usoRAM'] and variation > 80 else random.randint(0, 1)

                        timestamp = f"{current_date.strftime('%Y-%m-%d')} {hour:02d}:00:00"

                        insert = f"""    ((SELECT idMaquinaRecurso FROM ServGuard.MaquinaRecurso 
        WHERE fkMaquina = 1 AND fkRecurso = (SELECT idRecurso FROM ServGuard.Recurso WHERE nome = '{resource}')), 
        {variation}, '{timestamp}', {is_alert})"""
                        inserts.append(insert)

                    file.write(',\n'.join(inserts) + ';\n')

if __name__ == "__main__":
    generate_sql_inserts()
    print("Arquivo 'inserts_captura_ultimas_5_semanas.txt' gerado com sucesso!")
