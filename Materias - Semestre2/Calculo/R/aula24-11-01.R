
acao <- read.csv("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base-r-wege.csv")

acao$id<-seq(1:48)

modelo1<-lm(acao$Close~acao$id)
summary(modelo1)

plot(acao$Close)
abline(modelo1)

modelo_poli<-lm(acao$Close~poly(acao$id,3))
summary(modelo_poli)
plot(acao$Close)
lines(sort(acao$id),fitted(modelo_poli)[order(acao$id)],col="red",type="l")


#EQUACAO DE TERCEIRO GRAU
modelo_poli$coefficients
library(ggplot2)
x<-acao$id
y<-acao$Close
ggplot(acao,aes(id,Close))+geom_point()+geom_smooth(method = "lm", formula = y~poly(x,3))

modelo_log<-lm(acao$Close~log(acao$id))
summary(modelo_log)
ggplot(acao,aes(id,Close))+geom_point()+geom_smooth(method = "lm", formula = y~log(x,3))
