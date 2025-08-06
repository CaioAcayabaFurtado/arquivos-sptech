#Tupla é IMUTAVEL, ou seja, mais segura, indexavel 

tup = (4,5,6)
print(type(tup))
lista = [1,2,3,4]
print(type(lista))

print("\n")

nested_tup = ((4, 5, 6), (7, 8)) #uma tupla interna
print(nested_tup)
print(nested_tup[0])

print("\n")

tup = ('foo', [1, 2, 3], True)
a = (6, 0)
b = ('bar',)
tup = tup + a + b #soma de tupla
print(tup)

print("\n")

tup2 = (1,2,3)
a,b,c = tup2 #atribuir nome as posicoes
print(a)

print("\n")

senha = "sptech"
senha2 = tuple(senha) #transformando em tupla
print(senha2)

print("\n")

values = ('ola', 'mundo', 'python', 'java', 'mysql', 'oracle')
c, d, *_ = values #O "*_" é o "resto" da tupla
print(*_)



print("\n")
print("\n")
print("\n")
print("\n")
print("\n")
print("\n")



#Lista é mutavel e indexavel
tup = ("foo", "bar", "baz")
b_list = list(tup)
print(b_list)

print("\n")

c_list = ['foo', 'bar', 'baz']
print(c_list)
c_list.append('dwarf') #adiciona na ultima posicao
c_list.insert(1, 'red') #adiciona em uma posicao especifica // melhor por ser mais leve
print(c_list)
c_list.pop(1)#remove algo da lista com a posicao
print(c_list)

print("\n")

x_list = [4, None, 'foo']
print(x_list)
x_list.extend([7, 8, (2, 3)])
print(x_list)

print("\n")

seq = [7, 2, 3, 7, 5, 6, 0, 1]
print(seq[1:4]) #fatiamento - seleciona um intervalo
seq.sort()#ordena por grandeza
print(seq)



print("\n")
print("\n")
print("\n")
print("\n")
print("\n")
print("\n")



#Dicionario

a = {}
print(type(a))
a = set([])
print(type(a))
a = [1,2,3]
print(a[0])

print("\n")

d1 = {"a": "algum valor", "b": [1, 2, 3, 4]}
print(d1.keys())
print(d1.values())
print(d1.items())

print("\n")
print("\n")
print("\n")

#"""Usando um dicionário para representar notas de uma turma"""
planilha_notas = {
    'Julia': [9.2, 8.5, 10.0],
    'Eduardo': [8.3, 9.5, 7.9],
    'Marcio': [9.1, 8.9, 8.2],
    'Reis': [9.7, 9.1, 9.2]
}
total_notas = 0
count_notas = 0

for nome, nota in planilha_notas.items():
    total = sum(nota)
    print(f'A média de {nome} é: {total / len(nota):.2f}')
    total_notas += total
    count_notas += len(nota)

print(f'A média da classe é de: {total_notas / count_notas:.2f}')

print("\n")
print("\n")
print("\n")

"""Tokenizando uma frase (string) e contando as palavras únicas"""

texto = ('Uma das maiores operadoras de linhas de transmissão do país (Taesa) '
         'a empresa tem um nível de endividamento alto '
         'decorrente de grandes investimentos em projetos que começaram do zero '
         'Porém o BBA destaca que a companhia tem perfil de dívida alongado e ' 
         'junto às receitas previsíveis das suas transmissoras ' 
         'tem uma flexibilidade financeira adequada')

contagem_palavras = {}

# conta as ocorrências de cada palavra única
for palavra in texto.split():
    if palavra in contagem_palavras:
        contagem_palavras[palavra] += 1 # atualiza o par chave-valor existente
    else:
        contagem_palavras[palavra] = 1 # insere um novo par de chave-valor

print(f'{"PALAVRA":<15}CONTAGEM')

for palavra, count in sorted(contagem_palavras.items()):
    print(f'{palavra:<15}{count}')

print('\nNúmero de palavras únicas:', len(contagem_palavras))
