
#at3:

x<-c(10:20)*20
x

#at5:

prob<- 120/180


#at9:

desempenho <- read.delim("C:/Users/cacay/Downloads/desempenho.txt")
print(desempenho)

#1)Qual a média e 1º quartil do seu conjunto de dados para a coluna matemática.
summary(desempenho$matematica)

#2)Determine a quantidade de alunos por genero
qtdMasc<-sum(desempenho$genero == "male")
qtdFem<-sum(desempenho$genero == "female")
print(qtdMasc) #91
print(qtdFem) #109

#3)Qual é a faixa que representa 65% da frequência da maior faixa dos dados 
#de literatura? Escolha a alternativa e justifique. 
hist(desempenho$literatura)
#a)40 – 45

#b)50 – 55

#c)60 – 65

#d)45 – 50 x

#utilizando um histograma podemos observar as faixas de frequencia, onde as duas
#principais colunas estao entre 40 e 50, porém a que se sobresai é a faixa de
#45-50

#4)Com base no data set desempenho, obtivemos este resultado. Obtenha o gráfico 
#no R usando o comando boxplot
boxplot(desempenho$literatura[desempenho$genero == "male"], desempenho$literatura[desempenho$genero == "female"])
#Descreva a análise dos resultados do gráfico boxplot. 


