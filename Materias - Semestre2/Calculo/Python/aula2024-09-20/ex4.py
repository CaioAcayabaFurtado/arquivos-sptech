import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt

# Formule uma equação de regressão linear avaliando sua qualidade e buscando responder 
# quantos sacos de trigo por hectare teríamos ao notar um índice pluviométrico de 9,5, 17,1 
# e 6,8

xT = np.array([12.9, 7.2, 11.3, 18.6, 8.8, 10.3, 15.9, 13.1]) # Precipitação pluviométrica (em centímetros)
yT = np.array([62.5, 28.7, 52.2, 80.6, 41.6, 44.5, 71.3, 54.4]) # Safra de trigo (em sacos por hectare)
valor = np.array([9.5, 17.1, 6.8])

#Precipitação em 9,5
a, b = st.linear_regression(xT, yT)
solucao = stats.linregress(xT,yT) 
a, b = solucao.slope, solucao.intercept
def formula(a,b,x): 
    return a*x + b 
    #a= slope
    #x= lista de comparacao ao a
    #b= intercept
def graph(a,b): 
    x = valor
    y = formula(a,b,x)
    plt.scatter(xT,yT) #pontos
    plt.plot(x,y) #linha
    plt.xlabel('Precipitação pluviometrica')
    plt.ylabel('Safra de trigo (qtd de sacos)')
    plt.title('grafico de dispersao')
    plt.grid()
    plt.show() #exibe o grafico
graph(a, b)

