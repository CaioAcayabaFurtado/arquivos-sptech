#at4
a = 4/6
a1 = 3/2
a = (a*a1)+1
b=pow(a,3)
a1=pow(3,b)

print(a)

#at5
a=4
b=2
c=a/b
print(type(c))
print(c)

#at8
s1 = [1,2,3]
s2 = [4,5,6]
print(s1+s2)

#at11
a=4/6
b=3/2
c=(a*b)+1
d=pow(3,2/3)
e=pow(d,c)
f=1.4+e
print(round(f,3))

#at12
s1={1,2,3}

#at13
a1 = 114.6
a2 = 114,8

# print(round(a1))
# print(round(a2))

#at22
print(2*2**3)

#at25
altura = 1.75
peso = 85
imc = (peso/pow(altura,2))
# rest = ceil(imc)

# at2
print( ((45/8)**(2+1.5))/(1-(5/7 + 6)/4) + 600)

#at9
maquinas = {
    "maquina_1": {"Memória RAM": 1, "Placa de Rede": 2, "CPU": 3},
    "maquina_2": {"Memória RAM": 1, "Placa de Rede": 2, "CPU": 3},
    "maquina_3": {"Memória RAM": 1, "Placa de Rede": 2, "CPU": 333}
}
print(maquinas["maquina_3"]["CPU"])


print (-4**1/2)
print(-pow(4,0.5))


try: 

   list = 5*[0]+5*[10] 
   x = list[9] 
   print('Done!') 
except IndexError: 
   print('Index out of Bond!') 
else: 
   print('Nothing is wrong!') 
finally: 
   print('Finally block!')


print(2*{1,2,3})