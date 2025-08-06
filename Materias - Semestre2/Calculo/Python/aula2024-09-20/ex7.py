import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt
import statsmodels.api as sm

# Os dados a seguir representam as eficácias percentuais de um analgésico e a quantidade 
# em miligramas de três medicamentos presentes em cada cápsula 

xT = np.array([[15, 20, 10], [15, 20, 10], [15, 20, 10], [15, 20, 10], [30, 20, 20], [30, 20, 20], [30, 30, 20], [30, 30, 20], [45, 30, 20], [45, 30, 20]])
yT = np.array([47, 54, 58, 66, 59, 67, 71, 83, 85, 94])

print("""Ex1 - Calcule a equação de regressão, comentando sobre a qualidade da reta 
encontrada. """)

x_const = sm.add_constant(xT)
modelo = sm.OLS(yT, x_const) 
resultado = modelo.fit()
print(resultado.summary())

intercept = resultado.params[0]
x1 = resultado.params[1]
x2 = resultado.params[2]
x3 = resultado.params[3]

print("Considerando o R-squared(0.8), o valor dele está próximo de 1, indicando que a qualidade da correlaçao é alta, ou seja, os pontos não estão tão distante da reta")

print("""Ex2 - Utilize essa equação para estimar a eficácia percentual média de cápsulas 
contendo 12,5 mg do medicamento A, 25 mg do medicamento B e 15 mg do 
medicamento C """)

valor = [12.5, 25, 15]
eficaciaEstimada = intercept + x1 * valor[0] + x2 * valor[1] + x2 * valor[2]
print(round(eficaciaEstimada, 2))