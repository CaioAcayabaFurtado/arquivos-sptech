from datetime import datetime, timedelta
import random

def generate_sql_inserts_high_peaks():
    # Valores base para uma máquina com picos frequentes
    base_values = {
        'usoCPU': (50, 85),  # Uso alto na maioria do tempo
        'usoRAM': (40, 80),
        'usoTotal': (30, 70),
        'velocidadeDownload': (60, 120),
        'velocidadeUpload': (50, 110),
        'erroPacotesEntrada': (0, 10),
        'erroPacotesSaida': (0, 10),
        'descartePacotesEntrada': (0, 5),
        'descartePacotesSaida': (0, 5),
        'megabytesRecebidos': (100, 800),
        'megabytesEnviados': (50, 600),
        'pacotesEnviados': (500, 1500),
        'pacotesRecebidos': (600, 2000)
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

    with open('inserts_captura_maquina_picos.txt', 'w', encoding='utf-8') as file:
        file.write("-- Inserindo dados para uma máquina com muitos picos de uso alto\n")

        for week_start, week_label in weeks:
            file.write(f"\n-- {week_label}\n")

            for day in range(7):
                current_date = week_start + timedelta(days=day)
                file.write(f"\n-- Dia {current_date.strftime('%Y-%m-%d')}\n")

                for hour in range(24):
                    # Variação de uso por horário
                    time_of_day_factor = 1.0
                    if 8 <= hour < 18:  # Horário comercial
                        time_of_day_factor = random.uniform(1.3, 1.7)
                    elif 0 <= hour < 6:  # Horário de menor uso
                        time_of_day_factor = random.uniform(0.8, 1.2)

                    # Redução no fim de semana
                    weekday_factor = 1.0 if current_date.weekday() < 5 else random.uniform(0.7, 1.0)

                    # Picos frequentes
                    stress_factor = random.uniform(1.5, 3.0) if random.random() < 0.4 else 1.0  # 40% chance de pico

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
                            correlation = random.randint(0, 10) if variation > 80 else random.randint(0, 5)
                            variation = max(variation, correlation)

                        # Ajusta probabilidade de alerta
                        is_alert = 1 if resource in ['usoCPU', 'usoRAM'] and variation > 80 else random.randint(0, 1)

                        timestamp = f"{current_date.strftime('%Y-%m-%d')} {hour:02d}:00:00"

                        insert = f"""    ((SELECT idMaquinaRecurso FROM ServGuard.MaquinaRecurso 
        WHERE fkMaquina = 4 AND fkRecurso = (SELECT idRecurso FROM ServGuard.Recurso WHERE nome = '{resource}')), 
        {variation}, '{timestamp}', {is_alert})"""
                        inserts.append(insert)

                    file.write(',\n'.join(inserts) + ';\n')

if __name__ == "__main__":
    generate_sql_inserts_high_peaks()
    print("Arquivo 'inserts_captura_maquina_picos.txt' gerado com sucesso!")
