#Caio Acayaba Furtado, 03241026

#Dados:
horas_do_dia = ["09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"]
usuarios_ativos = [10, 12, 15, 25, 22, 18, 15, 20, 28, 30]
uso_cpu = [20.0, 25.2, 30.0, 45.1, 42.7, 33.6, 31.5, 45.0, 53.1, 60.2]

#Imports:
import statistics as st
import numpy as np

#-------------------------------------------------------------------------------------------------------------------------
 
#1. Estatísticas descritivas: 
#➢ Calcule a média, a mediana e o desvio padrão do número de usuários ativos e do uso da CPU. 
media_usuarios = round(sum(usuarios_ativos) / len(usuarios_ativos))
mediana_usuarios = round(st.median(usuarios_ativos))
desvioP_usuarios = round( st.stdev(usuarios_ativos))
print('user:',mediana_usuarios,'|', media_usuarios,'|', desvioP_usuarios)

media_cpu = round( sum(uso_cpu) / len(uso_cpu))
mediana_cpu = round( st.median(uso_cpu))
desvioP_cpu = round( st.stdev(uso_cpu))
print('cpu: ',mediana_cpu,'|', media_cpu,'|', desvioP_cpu)



#2. Análise de equações lineares: 
#➢ Com base nos dados, levante a hipótese se pode haver uma relação linear entre o número de usuários 
#ativos e o uso da CPU. Justifique sua hipótese. 

#Sim, de acordo com o aumento de usuários ativos, o uso da CPU também aumenta.


#➢ Se você acredita que existe uma relação linear, calcule a inclinação e a interceptação da linha de 
#melhor ajuste que descreve a relação entre o uso da CPU e os usuários ativos. 

# DADO DE COMPARAÇÃO = 9:00 - 10 - 20%
# f(x) = ax + b
# angulo = resultado(a).10+resultado(b)

usuarios_ativos = np.array([10, 12, 15, 25, 22, 18, 15, 20, 28, 30])
uso_cpu = np.array([20.0, 25.2, 30.0, 45.1, 42.7, 33.6, 31.5, 45.0, 53.1, 60.2])
print('\n')

a = np.array([[12, 1], [25.2, 1]])
b = np.array([[15, 1], [30.0, 1]])
solucao = np.linalg.solve(a, b)
print('Modelo 1:',solucao)




#3. Modelo de previsão: 
#➢ Supondo um relacionamento linear, crie uma equação linear para prever o uso da CPU (C) com base no 
#número de usuários ativos (U).

#➢ Utilizando algum modelo que você calculou preveja o uso da CPU se houver 20 usuários ativos às19:00. 



#4. Análise de equações não lineares: 
#➢ Considere a possibilidade de um relacionamento não linear entre o uso da CPU e os usuários ativos. 
#Discuta as razões pelas quais isso poderia acontecer.