import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt

# Regressão linear simples:

# Para verificar se um conservante de alimentos largamente utilizado contribui para a 
# hiperatividade de crianças em idade pré-escolar, um nutricionista escolheu uma amostra 
# aleatória de dez crianças de quatro anos reconhecidas como bastantes hiperativas de 
# várias escolinhas e observou seu comportamento 45 minutos depois de terem ingerido 
# quantidades controladas de comida contendo o conservante. Na tabela a seguir, x é a 
# quantidade de comida consumida contendo o conservante (em gramas) e y é uma 
# medição subjetiva de hiperatividade (numa escala de 1 a 20) baseada na agitação da 
# criança e na interação com outras crianças:

xT = np.array([36, 82, 45, 49, 21, 24, 58, 73, 85, 52])
yT = np.array([6, 14, 5, 13, 5, 8, 14, 11, 18, 6])

print("\n")
print('''Ex_1. Plote um gráfico de dispersão para decidir se uma reta pode descrever de modo 
razoável o comportamento geral dos dados''')
a, b = st.linear_regression(xT, yT)
solucao = stats.linregress(xT,yT) 
a, b = solucao.slope, solucao.intercept
def formula(a,b,x): 
    return a*x + b 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept
def graph(a,b): 
    x = xT
    y = formula(a,b,x)
    plt.scatter(xT,yT) #pontos
    plt.xlabel('qtd de conservante')
    plt.ylabel('nivel de hiperatividade')
    plt.title('grafico de dispersao')
    plt.grid()
    plt.show() #exibe o grafico
graph(a, b)



print("\n")
print('''Ex_2. Calcule a regressão linear dos dados e plote essa linha no gráfico de dispersão.''')
a, b = st.linear_regression(xT, yT)
solucao = stats.linregress(xT,yT) 
a, b = solucao.slope, solucao.intercept
def formula(a,b,x): 
    return a*x + b 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept
def graph(a,b): 
    x = xT
    y = formula(a,b,x)
    plt.scatter(xT,yT) #pontos
    plt.plot(x,y) #linha
    plt.xlabel('qtd de conservante')
    plt.ylabel('nivel de hiperatividade')
    plt.title('grafico de dispersao')
    plt.grid()
    plt.show() #exibe o grafico
graph(a, b)



print("\n")
print('''Ex_3. Use a equação de regressão para estimar a medida de hiperatividade de uma 
dessas crianças que ingeriu 65 gramas de comida com o conservante 45 minutos 
antes''')
a, b = st.linear_regression(xT, yT)
x = 65
def estimativa(a,b,x): 
    print ("A estimativa de hiperatividade é: ", round(a*x + b)) 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept
estimativa(a,b,x)


print("\n")
print('''Ex_4. Discuta os valores encontrados e a precisão da reta construída''')
print('''Observando o gráfico, percebemos que o R_Value está em um nível muito baixo, ou seja, 
      os pontos estão muito dispersos em relação a reta criada, dando a entender que a quantidade
      de conservante nos alimentos talvez não tenha uma relação direta com a hiperatividade das
      crianças.''')


