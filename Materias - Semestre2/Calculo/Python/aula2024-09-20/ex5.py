import statistics as st
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt
import statsmodels.api as sm

# Regressão linear múltipla 
# Os dados a seguir mostram o número de quartos, o número de banheiros e os preços 
# pelos quais oito casas de um  certo bairro foram vendidas recentemente

xT = np.array([[3, 2], [2, 1], [4, 3], [2, 1], [3, 2], [2, 2], [5, 3], [4, 2]])
yT = np.array([143800.00, 109300.00, 158800.00, 109200.00, 154700.00, 114900.00, 188400.00, 142900.00 ])

print("""Ex1 - Encontre uma equação linear que permita prever o preço de venda médio de uma 
casa no bairro dado, em termos de número de quartos e do número de banheiros. """)

x_const = sm.add_constant(xT)
modelo = sm.OLS(yT, x_const) 
resultado = modelo.fit()
print(resultado.summary())
coeficientes = resultado.params
print("\n", round(coeficientes[0], 2), "/", round(coeficientes[1], 2), "/", round(coeficientes[2], 2))


print("""Ex2 - Caso eu queira uma casa com 3 quartos e 1 banheiro, quanto seria o preço 
estimado?""")

novaCasa = [1, 3, 1]
precoEstimado = resultado.predict(novaCasa)
print(round(precoEstimado[0], 2))