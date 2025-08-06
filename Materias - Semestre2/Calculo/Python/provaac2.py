import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt
import statsmodels as sm

# Em um  estudo de poluição no curso de água do Rio Pinheiros, a concentração de poluição é medida em 5 locais diferentes. 
# Os locais estão a distâncias diferentes da fonte de poluição. Essas distâncias e a poluição média são dados:

# x. Distância até a fonte de poluição (em km): 2; 4; 6; 8;10
x = np.array([2,4,6,8,10])
# y. Concentração média: 11,5; 10,2; 10,3; 9,68; 9,32
y = np.array([11.5,10.2,10.3,9.68,9.32])

# Quais são as estimativas para os dois parâmetros desconhecidos em um  modelo de regressão linear usual: coeficiente 
# angular e interceptação
# x = n
solucao = stats.linregress(x,y) 
print(solucao)
# estimativa = np.linalg.solve(x,y)

# x_const = sm.add_constant(x)
# modelo = sm.OLS(y, x_const) 
# resultado = modelo.fit()
# print(resultado.summary())
# estimado = resultado.predict(x)
# coeficientes = resultado.params()