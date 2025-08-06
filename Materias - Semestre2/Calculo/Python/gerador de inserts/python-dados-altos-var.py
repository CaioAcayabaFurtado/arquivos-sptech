from datetime import datetime, timedelta
import random

def generate_sql_inserts_sparse_peaks():
    # Valores base para uma máquina com picos esparsos
    base_values = {
        'usoCPU': (20, 70),  # Menores valores base
        'usoRAM': (15, 60),
        'usoTotal': (10, 50),
        'velocidadeDownload': (40, 100),
        'velocidadeUpload': (30, 90),
        'erroPacotesEntrada': (0, 5),
        'erroPacotesSaida': (0, 5),
        'descartePacotesEntrada': (0, 3),
        'descartePacotesSaida': (0, 3),
        'megabytesRecebidos': (50, 400),
        'megabytesEnviados': (20, 300),
        'pacotesEnviados': (200, 800),
        'pacotesRecebidos': (300, 900)
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

    with open('inserts_captura_picos_esparsos.txt', 'w', encoding='utf-8') as file:
        file.write("-- Inserindo dados para uma máquina com picos dispersos e esparsos\n")

        for week_start, week_label in weeks:
            file.write(f"\n-- {week_label}\n")

            for day in range(7):
                current_date = week_start + timedelta(days=day)
                file.write(f"\n-- Dia {current_date.strftime('%Y-%m-%d')}\n")

                for hour in range(24):
                    # Fator horário
                    time_of_day_factor = 1.0
                    if 8 <= hour < 18:  # Horário comercial
                        time_of_day_factor = random.uniform(1.1, 1.4)
                    elif 0 <= hour < 6:  # Horário de menor uso
                        time_of_day_factor = random.uniform(0.8, 1.1)

                    # Redução no fim de semana
                    weekday_factor = 1.0 if current_date.weekday() < 5 else random.uniform(0.7, 1.0)

                    # Picos esparsos
                    stress_factor = random.uniform(1.5, 3.0) if random.random() < 0.15 else 1.0  # 15% chance de pico

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
        WHERE fkMaquina = 5 AND fkRecurso = (SELECT idRecurso FROM ServGuard.Recurso WHERE nome = '{resource}')), 
        {variation}, '{timestamp}', {is_alert})"""
                        inserts.append(insert)

                    file.write(',\n'.join(inserts) + ';\n')

if __name__ == "__main__":
    generate_sql_inserts_sparse_peaks()
    print("Arquivo 'inserts_captura_picos_esparsos.txt' gerado com sucesso!")
