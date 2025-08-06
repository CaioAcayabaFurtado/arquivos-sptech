import statsmodels.api as sm
import numpy as np

#Sistemas lineares – case servidores

# Em três servidores web, situados na mesma zona de disponibilidade, os dados de tráfego de 
# requisição web HTTP tem diferentes quantidades de GET e POST, intercaladas arbitrariamente.

# Um servidor central de homologação recebe parte das requisições dos três servidores para se 
# realizar testes de stress, carga e concorrência nas APIs.

# A cada 10 requisições que serão enviadas para esse servidor de teste, quantas requisições de cada 
# servidor se deve enviar?

# Chamaremos de q1g a quantidade de GET no servidor 1, q1p a quantidade de POST no servidor 1, 
# e assim por diante. Essa informação é conhecida previamente. As quantidades que queremos 
# obter são chamadas de qGe qP. As incógnitas são as quantidades relativas de requisições de cada 
# servidor que colocaremos no servidor de teste, que chamaremos de t1, t2 e t3. Elas são medidas 
# em quantidades, e devem ser tais que: 
    # t1 + t2 + t3 = 10

# Além disso, as quantidades da requisição GET após a mistura dos três será dada por: 
    # q1g . t1+ q2g . t2 + q3g . t3

# Pensando o mesmo sobre as requisições de POST, ficamos com três equações lineares e três 
# incógnitas:
    # q1g . t1+ q2g . t2 + q3g . t3 = qG
    # q1p . t1 + q2p . t2 + q3p . t3 = qP
    # t1 + t2 + t3 = 10

# Supondo que após alguns experimentos, as quantidades de requisições sejam dispostas a seguir, 
# quantas requisições aproximadamente cada servidor deve enviar para o servidor de teste por vez?
    # 115 . t1 + 100 . t2 + 125 . t3 = 1.100 (get)
    # 240 . t1 + 150 . t2 + 50 . t3 = 1.750 (post)
    # t1 + t2 + t3 = 10 (quantidades relativas de requisições)

print('''Exercicio: Supondo que após alguns experimentos, as quantidades de requisições sejam dispostas a seguir, 
         quantas requisições aproximadamente cada servidor deve enviar para o servidor de teste por vez?''')

a = np.array([[115, 100, 125], [240, 150, 50], [1, 1, 1]])
b = np.array([1100, 1750, 10])

solucao = np.linalg.solve(a, b)
print('Servidor 1:', round(solucao[0], 2))
print('Servidor 2:', round(solucao[1], 2))
print('Servidor 3:', round(solucao[2], 2))

