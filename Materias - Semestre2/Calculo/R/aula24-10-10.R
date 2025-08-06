p<-200000
n<-15000

pop.escolaridade<-rep(c(0,1,2,3,4,5,6),p)
set.seed(15)
escolaridade.temp<-sample(pop.escolaridade,n)
hist(escolaridade.temp)
escolaridade<-factor(escolaridade.temp,
                     levels = c(0,1,2,3,4,5,6),
                     labels = c('analfabeto','1°grau','2°grau','3°grau', 
                                'mestrado', 'doutorado', 'posdoc'),
                     ordered= TRUE)

rm(pop.escolaridade,escolaridade.temp)
str(escolaridade)
table(escolaridade)
prop.table(table(escolaridade))

fumante.n<-rbinom(n,1, .40)
fumante.f<-factor(fumante.n,
                 levels = c(0,1),
                 labels = c("nao","sim"),
                 ordered = TRUE)

str(fumante.f)
summary(fumante.f)
str(fumante.n)
mean(fumante.n)

table(fumante.f) #contagem
prop.table(table(fumante.f)) #em %  

par(mfrow = c(1,2))#dois graficos um do lado do outro
barplot(table(escolaridade), ylab = "numero de participantes")
barplot(table(fumante.f), ylab = "numero de participantes")

par(mfrow = c(1,2))#dois graficos um do lado do outro
barplot(round(prop.table(table(escolaridade)),3),
        ylab = "proporcao de participantes",
        ylim = c(0,0.7))
barplot(round(prop.table(table(fumante.f)),3),
        ylab = "proporcao de fumantes",
        ylim = c(0,0.7))

#utilizar o ylim é importante pra manter a proporção entre os dois graficos

#apenas o table pode fazer essa comparacao de duas variaveis
table(escolaridade, fumante.f)

tabelaProp <- round(prop.table(table(escolaridade, fumante.f)), 3)*100
tabelaProp

install.packages("scales")


mosaicplot(prop.table(table(escolaridade,fumante.f)),
           col = c("#f2a6b8","#b5d3e3"),
           main = "")
