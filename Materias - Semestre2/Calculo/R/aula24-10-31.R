captura <- read.csv("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base-r-notas.csv")

#priemira visao geral da relacao
plot(captura$nota.algo, captura$nota.arqcomp)

#REGRESSAO LINEAR
modeloNotas<- lm(captura$nota.arqcomp~captura$nota.algo)
summary(modeloNotas)
modeloNotas


par(mfrow=c(2,2))
plot(modeloNotas,which=1:4)
abline(modeloNotas)


nota_score<-(captura$nota.arqcomp+captura$nota.banco)/2
modeloNotas2<-lm(captura$nota.algo~nota_score)
plot(modeloNotas)
abline(modeloNotas2)

#RELACAO ENTRE RECURSOS PODE SER ASSIM:
pairs(captura)
library(GGally)
ggpairs(captura)

#REGRESSAO LINEAR MULTIPLA
modeloNotas3<-lm(captura$nota.algo~captura$nota.arqcomp+captura$nota.banco)
hist(residuals(modeloNotas3))

#HOMOCEDASTICIDADE(MUDANCA PROPORCIONAL ENTRE X E Y)
#HETEROCEDASTICIDADE(MUDANCA DESPROPORCIONAL ENTRE X E Y)
plot(fitted(modeloNotas3),residuals(modeloNotas3))
abline(h=0,lty=2)
