#Caio Acayaba Furtado, 03241026


import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt

#----------------------------------------------------------------------------------------
# EQUAÇÃO DA RETA
usuario = [10, 12, 15, 25, 22, 18, 15, 20, 28, 30]
uso_cpu = [20, 25.2, 30, 45.1, 42.7, 33.6, 31.5, 45, 53.1, 60.2]
a, b = st.linear_regression(usuario, uso_cpu)
# print(f'eq. da reta: y = {a:.2f}x + {b:.2f}')
#Eq. da reta: y = 1.85x + 2.62
#----------------------------------------------------------------------------------------
# PLOTAGEM DE GRÁFICO
usuario = np.array([10, 12, 15, 25, 22, 18, 15, 20, 28, 30])
uso_cpu = np.array([20, 25.2, 30, 45.1, 42.7, 33.6, 31.5, 45, 53.1, 60.2]) 
solucao = stats.linregress(usuario,uso_cpu) 
a, b = solucao.slope, solucao.intercept

def formula(a,b,x): 
    return a*x + b 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept

def graph(a,b): 
    x = usuario
    y = formula(a,b,x)
    plt.scatter(usuario,uso_cpu) #pontos
    plt.plot(x,y) #linha
    plt.xlabel('usuários')
    plt.ylabel('uso da CPU [%]')
    plt.title('uso da cpu por usuário logado')
    plt.grid()
    plt.show() #exibe o grafico

# graph(a, b) - CHAMA A FUNÇÃO
#----------------------------------------------------------------------------------------
print("\n")
print("---EXERCICIO-1---")


#Exercicio1
# Você é um investidor individual que está pensando em fazer um pé de meia. Você analisou os 
# rendimentos de um título do tesouro atrelado à inflação (IPCA+2045) e coletou dados sobre os 
# rendimentos (Y) durante um período. Seu principal interesse está em compreender as relações entre os 
# rendimentos desse título e seus períodos de vencimento (M)

vencimento = [18, 5, 11, 9, 14, 6, 13, 8, 22, 15, 7, 20, 19, 16, 21, 10, 17, 12]
rendimento = [2.9, 4.2, 3.2, 3.8, 4.0, 4.5, 3.4, 3.7, 2.1, 4.7, 4.3, 2.7, 2.5, 4.1, 4.3, 3.5, 3.5, 3.6]

# a) Calcule a média, mediana e desvio padrão dos rendimentos do título.
print("media vencimento:", st.mean(vencimento))
print("media rendimento:", st.mean(rendimento))

print("mediana vencimento:", st.median(vencimento))
print("mediana rendimento:", st.median(rendimento))

print("desvio padrao vencimento:", st.pstdev(vencimento))
print("desvio padrao rendimento:", st.pstdev(rendimento))

# b) Formule um sistema linear de equações para representar a relação entre os rendimentos do título 
# e os períodos de vencimento.
#    y = rendimento.x+vencimento
a, b = st.linear_regression(rendimento, vencimento)
print(f'eq. da reta: y = {a:.2f}x + {b:.2f}')


# c) Analise os coeficientes obtidos na resolução dos sistemas lineares. Que insights você pode obter 
# sobre a sensibilidade dos rendimentos aos períodos de vencimento para esse tipo de título? 

print("""
    Entendendo que na equação o eixo X tem como resultado negativo (-4.11), entende-se que 
    quanto maior o rendimento, menor o vencimento.
""")

# d) Com base na sua análise, forneça recomendações aos investidores interessados em títulos do 
# tesouro com diferentes prazos de vencimento.

print("""
    No caso de investimentos pequenos, não seria interessante, já que o tempo de vencimento ia ser muito grande.
    Porém, com investimentos de grande rendimento, é uma otima oportunidade, já que quanto maior o rendimento, 
    menor o tempo de vencimento.
""")

print("\n")
print("\n")
print("\n")

#----------------------------------------------------------------------------------------
print("---EXERCICIO-2---")

# Exercício 2:Fotografia
# Como uma amante da fotografia que gosta de explorar a relação entre as configurações da câmera e o 
# brilho das fotografias, você coletou dados sobre as configurações de uma abertura (A) específica (f1/4), 
# configurações de velocidade do obturador (S) e os valores correspondentes de brilho da imagem (B). 
# Você deseja entender como essas configurações afetam o brilho geral de suas fotos.

velocidade_do_obturador = [0.5,0.25,0.125,0.0667,0.0333,0.0167,0.008,0.004,0.002,0.001]
brilho = [200, 190, 175, 171, 168, 150, 148, 140, 131, 127]

# a) Calcule a moda, mediana, os decils, a variância e desvio padrão dos valores de brilho.
print("media velocidade_do_obturador:", st.mean(velocidade_do_obturador))
print("media brilho:", st.mean(brilho))

print("mediana velocidade_do_obturador:", st.median(velocidade_do_obturador))
print("mediana brilho:", st.median(brilho))

print("desvio padrao velocidade_do_obturador:", st.pstdev(velocidade_do_obturador))
print("desvio padrao brilho:", st.pstdev(brilho))
# b) Crie um gráfico de dispersão para visualizar a relação entre as configurações de abertura e o 
# brilho da imagem.

velocidade_do_obturador = np.array([0.5,0.25,0.125,0.0667,0.0333,0.0167,0.008,0.004,0.002,0.001])
brilho = np.array([200, 190, 175, 171, 168, 150, 148, 140, 131, 127])
solucao = stats.linregress(velocidade_do_obturador,brilho) 
a, b = solucao.slope, solucao.intercept

def formula(a,b,x): 
    return a*x + b 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept

def graph(a,b): 
    x = velocidade_do_obturador
    y = formula(a,b,x)
    plt.scatter(velocidade_do_obturador,brilho) #pontos
    plt.plot(x,y) #linha
    plt.xlabel('velocidade do obturador')
    plt.ylabel('brilho')
    plt.title('relação do brilho com velocidade')
    plt.grid()
    plt.show() #exibe o grafico

graph(a, b)

# c) Formule um sistema linear de equações para representar a relação entre as configurações de 
# velocidade do obturador e brilho da imagem.
a, b = st.linear_regression(velocidade_do_obturador, brilho)
print(f'eq. da reta: y = {a:.2f}x + {b:.2f}')

# d) Analise os coeficientes obtidos na resolução do sistema linear. Que informações você pode obter 
# sobre como as configurações de abertura e velocidade do obturador afetam o brilho da imagem?
print("""
    Quanto menor a velocidade do obturador, maior o brilho da foto.
""")
# e) Com base na sua análise, forneça recomendações aos fotógrafos que buscam atingir níveis 
# específicos de brilho em suas fotos
print("""
    Para medir a quantidade de brilho que quiser, basta aumentar ou diminuir a velocidade do obturador
    (quanto menor a velocidade, maior o brilho), já que com mais tempo de obturação, mais brilho é capturado.
""")