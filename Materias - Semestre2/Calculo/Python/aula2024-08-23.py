# -> Caio Acayaba Furtado, RA:03241026

# Legenda:
from math import pow #ferramenta para potência
# n = número 
# np = número da potência
# na = número agregado
# potencia = pow(n, np)

#Lista para automação:
resultado = []


# -------------------------------------------------------------------


#1 - Potência
n = 2
np = 3
potencia = pow(n, np) #variavel padrao para potencia
c1 = potencia
resultado.append(c1)


#2 - Potência com número negativo
n = -2
np = 3
potencia = pow(n, np) 
c2 = potencia
resultado.append(c2)


#3 - Potênciado por 0
n = 1
np = 0
potencia = pow(n, np) 
c3 = potencia
resultado.append(c3)


#4 - Potênciado por 0 com número negativo
n = -1
np = 0
potencia = pow(n, np) 
c4 = potencia
resultado.append(c4)


#5 - Potênciado por 0
n = 2
np = 0
potencia = pow(n, np) 
c5 = potencia
resultado.append(c5)


#6 - Potência com divisão
n = 2
n2 = 5
np = 3
potencia = pow(n, np) 
potencia2 = pow(n2, np) 
c6 = potencia/potencia2
resultado.append(c6)


#7 - Potência negativa
n = 3
np = -2
potencia = pow(n, np) 
c7 = potencia
resultado.append(c7)


#8 - Potência negativa com divisão
n = 1
n2 = 2
np = -3
potencia = pow(n, np) 
potencia2 = pow(n2, np) 
c8 = potencia/potencia2
resultado.append(c8)


#9 - Multiplicação de potência
n = 3
np1 = 3
np2 = 4
np = np1*np2
potencia = pow(n, np) 
c9 = potencia 
resultado.append(c9)


#10 - Potência em número com vírgula
n = 0.5
np = 3
potencia = pow(n, np) 
c10 = potencia
resultado.append(c10)


#11 - Potência em número com vírgula
n = 0.25
np = 4
potencia = pow(n, np) 
c11 = potencia
resultado.append(c11)


#12 - Potência com 0
n = 0
np = 4
potencia = pow(n, np) 
c12 = potencia
resultado.append(c12)


#13 - Potência com soma
n = 0.41
np = 2
na = 1
potencia = pow(n, np) 
c13 = na + potencia
resultado.append(c13)


#14 - Duas potência com soma
n = 5
np = 2
n2 = 2
np2 = -4
na = 1/4
potencia = pow(n, np) 
potencia2 = pow(n2, np2) 
c14 = (na + potencia) - potencia2
resultado.append(c14)


#15 - Soma do resultado de duas potências

n = 2
np = -3
n2 = -4
np2 = -5
potencia = pow(n, np) 
potencia2 = pow(n2, np2) 
c15 = potencia + potencia2
resultado.append(c15)


#16 - Soma do resultado de duas potências

n = (4/5) - (1/2) + 1
np = -2
n2 = 3
np2 = 2
n3 = (4-5)
np3 = -2
potencia = pow(n, np) 
potencia2 = pow(n2, np2) 
potencia3 = pow(n3, np3) 
c16 = potencia + (1/(1+(potencia2)-potencia3))
resultado.append(c16)



#Contagem para exibição:
c=0
while c<16:
    print(f'Exercício {c+1}:', resultado[c])
    c+=1
    