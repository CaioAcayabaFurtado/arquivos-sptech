#criar data frame
alt <- c(3,4,3,2,5,2,3,1,3,2,6,5,4,8,6,3,5,3,7,8)
luminosidade <- rep(c("muita","pouca"),each=10)
dados <- data.frame(alt,luminosidade)
dados
str(dados)

#criar outro objeto no data frame
fol <- c(19,21,18,18,16,21,23,21,25,22,9,7,6,7,12,9,12,2,9,4)
dados$fol <- fol
dados

#exibicao
table(dados$luminosidade)
names(dados)
head(dados)
tail(dados, n=2) #final da tabela e definindo quantas linhas quer
dados[1,]#ver uma linha com todas as colunas
dados[c(1,2,3),]#ver linhas especificas com todas as colunas
dados[,1]#ver uma coluna com todas as linhas

#criar subsets
subset(dados)
#criar subsets com "where"
subset(dados, luminosidade == "muita")

#parametros de simulacao
set.seed()

#dados simulados
p<-200000
n<-20000
set.seed(1234)
altura<- abs(round(rnorm(n,1.7,0.05),2))
summary(altura)
peso<- abs(round(rnorm(n,75,3.5),2))
summary(peso)
imc<- round(peso/altura**2, 2)
summary(imc)

#histograma
par(mfrow=c(2,2))
hist(altura, main='altura', ylab='freq', xlab='altura')
hist(peso, main='peso', ylab='freq', xlab='peso')
hist(imc, main='imc', ylab='freq', xlab='imc')

#----------------------------------------------------------------

#aula 24/10/04

salario<-abs(round(rnorm(n,3000,400),2))
summary(salario)



pop.carros<-rep(c(0,1,2,3),p)
carros<-sample(pop.carros,n)

pop.filhos<-rep(c(0,1,2),p)
filhos<-sample(pop.filhos,n)

par(mfrow=c(1,2))
hist(carros)
hist(filhos)




pop.escola<-rep(c(0,1,2,3,4,5,6),p)
esc.temp<-sample(pop.escola,n)
esc<-factor(esc.temp,
            levels=c(0,1,2,3,4,5,6),
            labels=c('analfabeto','1grau','2grau','3grau','msc','doc','posdoc'),
            ordered=TRUE)
str(esc)



fumante.n <- rbinom(n,1,0.40)
fumante.f<-factor(fumante.n,
                  levels=c(0,1),
                  labels=c('nao','sim'),
                  ordered=TRUE)
str(fumante.f)
table(fumante.f)
mean(fumante.n)



pop.sexo<-rep(c(1,2),p)
sexo.temp<-sample(pop.sexo,n)
sexo<-factor(sexo.temp,
             levels=c(1,2),
             labels=c('m','f'),
             ordered=FALSE)
str(sexo)
table(sexo)




pop.prof<-rep(c(0:2),p)
prof.temp<-sample(pop.prof,n)
prof<-factor(prof.temp,
             levels=c(0,1,2),
             labels=c('humanas','exatas','bio'),
             ordered=FALSE)
str(prof)
table(prof)


df<- data.frame(id=1:n,
                altura,
                carros,
                esc,
                filhos,
                fumante.f,
                fumante.n,
                peso)
str(df)
View(df)



#BOXPLOT

boxplot(altura)

