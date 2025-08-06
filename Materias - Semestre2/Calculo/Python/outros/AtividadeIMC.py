#Caio Acayaba Furtado, RA: 03241026

#Atividade 1 - Calcular IMC:

peso = float(input('Quantos quilos você pesa?(Kg)'))
altura = float(input('Qual é a sua altura?(m)'))
imc = peso/(altura**2)

print(f'Seu IMC é de {imc:.1f}'.format(imc))



#Atividade 2 - Cálculo com operador AND:

peso = float(input('Quantos quilos você pesa?(Kg)'))
altura = float(input('Qual é a sua altura?(m)'))
imc = peso/(altura**2)

print(f'Seu IMC é de {imc:.1f}'.format(imc))

if (imc<18.5):
    print('Abaixo do peso')
elif (imc >= 18.5 and imc<25):
    print('Peso normal')
elif (imc >= 25 and imc<30):
    print('Acima do peso')
elif (imc >= 30 and imc<40):
    print('Obeso')
elif (imc >=40):
    print('Obesidade mórbida')



#Atividade 3 - Cálculo entre faixas:

peso = float(input('Quantos quilos você pesa?(Kg)'))
altura = float(input('Qual é a sua altura?(m)'))
imc = peso/(altura**2)

print(f'Seu IMC é de {imc:.1f}'.format(imc))

if (imc<18.5):
    print('Abaixo do peso')
elif (18.5<= imc <25):
    print('Peso normal')
elif ( 25 <= imc <30):
    print('Acima do peso')
elif (30 <= imc <40):
    print('Obeso')
elif (imc >=40):
    print('Obesidade mórbida')



#Atividade 4 - Calcular IMC:

peso = float(input('Quantos quilos você pesa?(Kg)'))
altura = float(input('Qual é a sua altura?(m)'))
imc = peso/(altura**2)

print(f'Seu IMC é de {imc:.2f}'.format(imc))