#Caio Acayaba, 03241026
#Gabriel Gomes, 03241005

import psutil 
import time
from datetime import datetime

print('\n')
print('Bem vindo ao monitoramento de máquina!')
escolha = input('Deseja capturar dados de memoria RAM?')

def capturarDados():
     #5 segundos por captura
     time.sleep(5)


     memoria = psutil.virtual_memory()
     total = memoria.total
     total = total / (1024 ** 3)
     total = round(total,1)

     available = memoria.available
     available = available / (1024 ** 3)
     available = round(available,1)

     percent = memoria.percent

     used = memoria.used
     used = used / (1024 ** 3)
     used = round(used,1)

     free = memoria.free
     free = total / (1024 ** 3)
     free = round(free,1)
     data = datetime.now().strftime('%d-%m-%Y %H:%M:%S')
     
     dicionario.append({'Total de memória RAM': total, 'Total de memória disponível': available, ' Uso da ram': percent, 'Memória usada': used, ' Total de memória livre': free, ' Data e hora': data})
          


if (escolha == 's'):
     qtd_resp = input('Digite quantos LOGs deseja realizar:')
     qtd = int(qtd_resp)

     dicionario = []
     
     contador = 0
     while contador < qtd:
          contador+=1
          capturarDados()
          print(f'{contador}° ciclo realizado...')

print('\n')
print('////TODOS OS LOGs AQUI!////')
print(dicionario)