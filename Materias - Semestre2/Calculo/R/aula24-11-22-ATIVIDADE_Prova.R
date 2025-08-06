#Caio Acayab Furtado, 03241026

#Você está trabalhando em um projeto de sistema acadêmico para o colégio de Winterhold e lhe 
#solicitam alguns insights sobre o último semestre de algumas turmas. Esses registros estão na 
#planilha denominada de base.xlsx. Só que ao analisar essa base você já percebe que terá que fazer 
#alguns ajustes. Então vamos começar! 

library(readxl)
dataset <- read_excel("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base.xlsx")

#=====================================================================================================

#1. Data cleaning: verifique se há valores nulos e decida como e se irá removê-los; 

dataset[is.na(dataset)] <- 0.00


#=====================================================================================================

#2. Data transformation: você percebe que para cada grande bloco de magia há três notas, 
#essas notas representam preliminares durante o semestre, é necessário que você crie uma 
#coluna para cada bloco de magia com a nota final da disciplina. Respeitando os pesos de 
#25% para a nota 1, 35% para a nota 2 e 40% para a nota 3; 


#ILLUSION
notaFinal <- (dataset$illusion.nota1 * 0.25)+(dataset$illusion.nota2 * 0.35) + (dataset$illusion.nota3 * 0.40)
dataset$NotaFinalIllusion <- notaFinal 

#CONJURATION
notaFinal <- (dataset$conjuration.nota1 * 0.25)+(dataset$conjuration.nota2 * 0.35) + (dataset$conjuration.nota3 * 0.40)
dataset$NotaFinalConjuration <- notaFinal

#RESTORATION
notaFinal <- (dataset$restoration.nota1 * 0.25)+(dataset$restoration.nota2 * 0.35) + (dataset$restoration.nota3 * 0.40)
dataset$NotaFinalRestoration <- notaFinal

#ALTERATION
notaFinal <- (dataset$alteration.nota1 * 0.25)+(dataset$alteration.nota2 * 0.35) + (dataset$alteration.nota3 * 0.40)
dataset$NotaFinalAlteration <- notaFinal

#DESTRUCTION
notaFinal <- (dataset$destruction.nota1 * 0.25)+(dataset$destruction.nota2 * 0.35) + (dataset$destruction.nota3 * 0.40)
dataset$NotaFinalDestruction <- notaFinal

#=====================================================================================================

#3. Cada grupo de magia também tem seu peso, então para calcular a nota final do cidadão siga 
#a seguinte regra: 
#a. Illusion: 15% 
#b. Conjuration: 15% 
#c. Restoration: 25% 
#d. Alteration: 25% 
#e. Destruction: 20% 

notaFinal2 <- (dataset$NotaFinalIllusion * 0.15)+(dataset$NotaFinalConjuration * 0.15)
                        + (dataset$NotaFinalRestoration * 0.25) + (dataset$NotaFinalAlteration * 0.25)
                        + (dataset$NotaFinalDestruction * 0.20)
dataset$NOTAFINAL <- notaFinal2


#4. Tendo a nota final calculada em cima das matérias crie uma coluna categórica seguindo a 
#regra de >= 6,0 aprovado e qualquer outra coisa reprovado; 

dataset$aprovacao<-ifelse(dataset$NOTAFINAL >= 6.0, "Aprovado", "Reprovado")

#=====================================================================================================

#5. Agora começa de fato a análise... 

#a. Separe por turma a quantidade de aprovados e reprovados com um percentual, 
#demonstre num gráfico de barras essas quantidades 

aprovados <- subset(dataset, aprovacao == "Aprovado")
reprovados <- subset(dataset, aprovacao == "Reprovado")
total_a <- nrow(aprovados)
total_r <- nrow(reprovados)
total <- nrow(dataset)
porcentagem_a <- (total_a/total)*100
porcentagem_r <- (total_r/total)*100
porcentagem<-c(porcentagem_a,porcentagem_r)

barplot(porcentagem,
        main="Porcentagem Aprovados X Reprovados",
        col = c("green", "red"))

#b. Calcule o percentual de pessoas que são aprovadas na matéria de Conjuration (nota 
#da matéria >= 6,0), se uma nova turma de 60 estudantes de magia se matricularem 
#nessa matéria qual a probabilidade de 15 pessoas ou menos reprovarem? 

dataset$aprovacaoConjuration<-ifelse(dataset$NotaFinalConjuration >= 6.0, "Aprovado", "Reprovado")

reprovados <- nrow(subset(dataset, aprovacaoConjuration == "Reprovado"))
porcentagem_r <- (reprovados/nrow(dataset))

probabilidade <- sum(dbinom(0:15, 60, porcentagem_r))
probabilidade

#c. Lydia, a professora de Destruction está preocupada com os alunos, você tenta 
#acalmá-la mostrando qual a probabilidade dos alunos de ficarem abaixo de 4,5 de 
#nota em sua matéria 

desvio_padrao <- sd(dataset$NotaFinalDestruction)
media <- mean(dataset$NotaFinalDestruction)

probabilidade <- pnorm(4.5, mean = media, sd = desvio_padrao)
probabilidade

#d. Já J'zargo acredita que ele precisa analisar alguns alunos, ele tem esse faro para 
#análises. A matéria dele (Restoration) é uma das mais concorridas, e com menos 
#tempo ele precisa ter uma visão geral dos focos que ele precisa agir. Mostre 
#graficamente como estão distribuídos seus alunos, ao mesmo tempo mostre uma 
#lista (se houver) de alunos que se destacam negativamente para ele poder atuar, e 
#positivamente para que ele possa designar feitiços mais avançados. 

boxplot(dataset$NotaFinalRestoration)

#quartis e IQR
Q1 <- quantile(dataset$NotaFinalRestoration, 0.25)  
Q3 <- quantile(dataset$NotaFinalRestoration, 0.75)  
IQR <- Q3 - Q1

limite_inferior <- Q1 - 1.5 * IQR
limite_superior <- Q3 + 1.5 * IQR

outliers <- dataset[dataset$NotaFinalRestoration < limite_inferior, ]


#e. Jarl Korir já gosta de analisar ao todo como estão os alunos, e ele sugere alguns 
#estudos mais específicos: 

#i. 
#Ele acha que pode existir alguma relação entre as matérias de Illusion e 
#Alteration, sendo a primeira determinante para o desempenho da 
#segunda. Mostre para ele um gráfico comparativo e faça uma modelagem 
#linear, verifique se é válida essa modelagem e comente com o Jarl. 

modeloNotas <- lm(dataset$NotaFinalIllusion ~ dataset$NotaFinalAlteration)

# Gráfico com melhorias visuais
plot(
  dataset$NotaFinalIllusion,
  dataset$NotaFinalAlteration,
  main = "Relação entre Notas de Illusion e Alteration", # Título do gráfico
  xlab = "Nota Final Illusion", # Rótulo do eixo X
  ylab = "Nota Final Alteration", # Rótulo do eixo Y
  col = "blue", # Cor dos pontos
  pch = 16, # Tipo de ponto
  cex = 1 # Tamanho dos pontos
)

# Adiciona a linha de regressão ao gráfico
abline(modeloNotas, col = "red", lwd = 2) # Cor e espessura da linha

# Adiciona uma legenda
legend(
  "topright", # Posição da legenda
  legend = c("Dados Observados", "Linha de Regressão"), 
  col = c("blue", "red"), 
  pch = c(16, NA), 
  lty = c(NA, 1), 
  lwd = c(NA, 2)
)


#ii. 
#Ele também acredita que Restoration e Destruction estão intimamente 
#ligadas, ele só não sabe se positivamente ou negativamente. Calcule para 
#ele se há alguma relação entre elas, se é algo expressivo. 

correlacao <- cor.test(dataset$NotaFinalRestoration, dataset$NotaFinalDestruction)
correlacao


#iii. 
#Por fim ele lhe pede que faça uma tabela final que correlacione todas as 
#notas finais, mostrando se há relevância estatística entre cada matéria. 
#(Sugestão de Balgruuf: a função cor() pode criar uma matriz de 
#correlação de um data frame. E de forma mais chique pode utilizar a 
#biblioteca corrplot e usar a função corrplot(cor(df))) 

correlacao <- cor(dataset[, c("NotaFinalConjuration", "NotaFinalDestruction", "NotaFinalIllusion", "NotaFinalRestoration", "NotaFinalAlteration")], use = "complete.obs")
print(correlacao)
library(GGally)
ggpairs(correlacao)
install.packages("corrplot")
library(corrplot)
corrplot(correlacao, method = "circle", type = "upper", 
         tl.col = "black", tl.srt = 45, 
         title = "Matriz de Correlação entre as Notas Finais", 
         mar = c(0, 0, 2, 0))
