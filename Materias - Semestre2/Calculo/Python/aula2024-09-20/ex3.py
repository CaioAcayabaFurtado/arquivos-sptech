import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt

# A tabela a seguir mostra durante quantas semanas seis pessoas estão trabalhando num 
# posto de inspeção de automóveis e quantos carros cada uma inspecionou entre o meio-dia e as 14 horas, em determinado dia:
xT = np.array([2, 7, 9, 1, 5, 12])  # Número de semanas trabalhadas
yT = np.array([13, 21, 23, 14, 15, 21])  # Número de carros inspecionados

print("\n")
print('''1. Calcule o coeficiente angular da reta e seu coeficiente linear''')
a, b = st.linear_regression(xT, yT)
print(round(a,2),round(b,2))



print("\n")
print('''2. Use esse resultado para poder estimar quantos automóveis pode-se esperar que 
uma pessoa inspecione durante o mesmo período de duas horas se ela está 
trabalhando no posto de inspeção há oito semanas''')
a, b = st.linear_regression(xT, yT)
x = 8
def estimativa(a,b,x):
    print(round(a*x+b))
estimativa(a,b,x)


print("\n")
print('''3. Exiba um gráfico com os dados e a reta de regressão formada''')
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
    plt.xlabel('qtd de semanas')
    plt.ylabel('qtd de carros')
    plt.title('grafico de dispersao')
    plt.grid()
    plt.show() #exibe o grafico
graph(a, b)