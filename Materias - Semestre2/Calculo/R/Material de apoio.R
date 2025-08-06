#MATERIAL DE R:

#DATA TRANSFORMATION:
#===============================================================================

data <- data.frame(x1 = c(1:4, 99999, 1, NA, 1, 1, NA),
                   x1 = c(1:5, 1, "NA", 1, 1, "NA"),
                   x1 = c(letters[c(1:3)], "x  x",  "x", "   y    y y", 
                          "x", "a", "a", NA),
                   x4 = "",
                   x5 = NA)
#alterando nome de colunas
colnames(data) <- c("col1", "col2", "col3", "col4", "col5")

#formatando campos vazios
data[data == ""] <- NA
data[data == "NA"] <- NA


library(dplyr)
data <- data %>% na.omit() #remove linhas com NA
data <- data %>% distinct(.keep_all=TRUE) #remove linhas duplicadas



#DATAFRAMES A PARTIR DE LISTAS:
#===============================================================================

alt <- c(3,4,3,2,5,2,3,1,3,2,6,5,4,8,6,3,5,3,7,8)
luminosidade <- rep(c("muita","pouca"),each=10)
dados <- data.frame(alt,luminosidade)
#adicionar coluna
fol <- c(19,21,18,18,16,21,23,21,25,22,9,7,6,7,12,9,12,2,9,4) 
dados$fol <- fol

#outra forma de fazer "where"
subset(dados, luminosidade == 'muita')



#SIMULACAO DE DADOS:
#===============================================================================

n<-100 
set.seed(1234)
idade <- abs(round(rnorm(n, 35, 7), 0))
summary(idade)

pop.esc <- rep(c(0,1,2,3,4,5,6), n) #criando categorias 
esc.temp <- sample(pop.esc, n) #gerando dados com as categorias
esc <- factor(esc.temp,
              levels=c(0,1,2,3,4,5,6),
              labels=c('analfabeto','1grau','2grau','3grau','msc','doc','posdoc'),
              ordered=TRUE) #somando os dados por categoria
str(esc)
table(esc)

#criando data frame
df <- data.frame(id = 1:n,idade)
df

#dados categoricos
set.seed(15) 
fumante.n <- rbinom(n, 1, .40) 
fumante.f <- factor(fumante.n, 
                      + levels = c(0,1), 
                      + labels = c("não", "sim"), 
                      + ordered = TRUE) 


par(mfrow = c(1,2)) #mostra os dois gráficos um do lado do outro 
barplot(table(escolaridade), ylab = "Número de participantes") 
barplot(table(fumante.f), ylab = "Número de participantes") 
#mistura dos dois:
mosaicplot(prop.table(table(escolaridade, fumante.f)), 
           col = c("#f2a6b8","#b5d3e3"), 
           main = "") 



#MODELO HISTOGRAMA E BOXPLOT:
#===============================================================================

boxplot(desempenho$literatura[desempenho$genero == "male"], desempenho$literatura[desempenho$genero == "female"])

#outliers
#limite Superior
#3quartil
#mediana
#1quartil
#limite inferior
#outliers


faixas <- seq(0, 100, by=10)
histograma <- hist(usoCPU$registro, #Dados utilizados
                   breaks=faixas, #Faixas de 10 em 10 %
                   freq=TRUE,
                   col = ("#4E2E9E"),
                   main = "Histograma de uso: CPU(%)",
                   xlab = "Faixas de uso (%)",
                   ylab = "Frequência",
                   right = FALSE, #Extende o y a maior frequencia histograma
                   xlim = c(0,100))#Fixa o valor de 0 a 100 na exibição 
ylim = c(0, (max(histograma$counts)+2))#Fixa o valor de 0 ao max do histograma 

#Colocando o valor de frequencia em cima das colunas.
text(x = histograma$mids, #Posição x
     y = histograma$counts, #Posição y
     labels = histograma$counts, #valor do texto
     pos = 3, #Ponto aonde o texto fica, no caso 3 significa acima da coluna 
     cex = 0.8,) #Tamanho do texto



#MODELAGEM LINEAR:
#===============================================================================

captura <- read.csv("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base-r-notas.csv")
modeloNotas<- lm(captura$nota.arqcomp ~ captura$nota.algo)
plot(captura$nota.arqcomp, captura$nota.algo)
abline(modeloNotas)


#RESIDUOS:

modeloNotas$residuals

#quanto menor e mais aleatorio melhor




#REGRESSAO POLINOMIAL:
#===============================================================================
acao <- read.csv("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base-r-wege.csv")
acao$id<-seq(1:48)

modelo1<-lm(acao$Close~acao$id)
plot(acao$Close)
abline(modelo1)
modelo_poli<-lm(acao$Close~poly(acao$id,3))
lines(sort(acao$id),fitted(modelo_poli)[order(acao$id)],col="red",type="l")


library(ggplot2) 
x <- acao$id 
y <- acao$Close 
ggplot(acao, aes(id, Close))+
geom_point()+
geom_smooth(method = "lm",formula = y~poly(x,3)) 



#REGRESSAO LOGARITIMICA:
#===============================================================================
ggplot(acao, aes(id, Close))+ 
     geom_point()+ 
     geom_smooth(method = "lm",formula = y~log(x)) 



#ANALISE COMBINATORIA:
#===============================================================================

# Permutação = combinação com todos os elementos.
factorial(5)


# Arranjo = combinacao com parte dos elementos (esolhe a quantidade de digitos na combinacao)
# São a quantidade de combinações de acordo com uma quantidade de digitos especifica.
calcular_arranjo <- function(n, r){ 
  if (n < r){ 
    return("N menor que 0")
  } else { 
    return(factorial(n) / factorial(n - r)) 
  }
} 
n <- 5 
qtdDigitos <- 2
# Se a qtdDigitos for igual a n, é uma Permutação.
arranjos <- calcular_arranjo(n, qtdDigitos) 
arranjos 


#Combinação
n <- 5 
r <- 2 
combinacoes <- choose(n, r)



#PROBABILIDADE BINOMIAL:
#===============================================================================

#D BINOM-----
#Exemplo: Suponha que Caio acerte 30% de chute a gol. Se ele bater 10 pênaltis, 
#qual é a probabilidade de marcar exatamente 4 gols?
x = 4
size = 10
prob = 0.3
dbinom(x,# valor eixo x (x = 0, 1, 2, ..., k) variavel independente          
      size, # número de tentativas (n >= 0) 
      prob) # a probabilidade de sucesso em cada tentativa 


#P BINOM-----
#Exemplo: Suponha que Caio acerte 30% de chute a gol. Se ele bater 10 pênaltis, 
#qual é a probabilidade de marcar 4 gols ou menos? 

pbinom(4, size=10, prob=0.3)


#R BINOM-----
#Exemplo:  
#Gerar um vetor que mostre o número de sucessos de 10 experimentos binomiais com 100 tentativas onde a 
#probabilidade de sucesso em cada tentativa é 0,3 
result <- rbinom(10, size=100, prob=0.3) 
result 


#Exemplo:
n <- 50  # número de tentativas 
p <- 0.75 # probabilidade de sucesso em uma única tentativa 

# a. probabilidade de 32 acessos com sucesso simultâneos 
prob_32 <- dbinom(32, size = n, prob = p) 
prob_32 

# b. probabilidade de no máximo 45 solicitações terem sucesso 
prob_45_tot <- pbinom(45, size = n, prob = p) 
prob_45_tot 

# c. sucesso em média esperado 
media_sucesso <- n * p 
media_sucesso 

# d. número mínimo para que tenha 90% de probabilidade de sucesso 
necessario_90_perc <- qbinom(0.9, size = n, prob = p) 
necessario_90_perc 


#DISTRIBUICAO NORMAL:
#===============================================================================

dados <- c(7.1,2.0,8.2,7.4,4.5,6.1,9.4,10.0,8.4,6.1,10.0,10.0,3.7,6.4, 
           7.4,8.4,6.8,8.4,8.3,7.8,2.9,4.5,8.4,4.8,6.5,8.4,8.4,8.7,7.8, 
           6.8,7.8,5.8,8.4,6.4,6.8,0.0,10.0,6.0,7.4,8.4,6.8,7.8,8.4,8.9, 
           5.5,7.0,5.8,10.0,10.0,3.1,4.6,5.6,4.9,6.2,5.2,1.9,6.8,6.3,6.4, 
           3.5,7.4,10.0,7.4,6.8) 

# Calcule a média e o desvio padrão das pontuações 
media_dados <- mean(dados) 
desvio_padrao_dados <- sd(dados)

# Defina a pontuação de corte 
pontuacao_corte <- 6 
# Calcule a probabilidade usando pnorm 
probabilidade <- 1 - pnorm(pontuacao_corte, mean = media_dados, sd = desvio_padrao_dados) 
probabilidade
# Crie uma sequência de valores para a curva da distribuição normal 
x <- seq(0, 11, length = 100) 
# Calcule a densidade de probabilidade para a sequência de valores 
densidade_probabilidade <- dnorm(x, mean = media_dados, sd = desvio_padrao_dados)
# Crie um gráfico comparando a curva da distribuição normal com os dados reais 
hist(dados, breaks = 10, prob = TRUE, main = "Distribuição de Pontuações de Teste", xlab = "Pontuação") 
lines(x, densidade_probabilidade, col = "red", lwd = 2) 



#Exemplo 2: Suponha que a altura das plantas em uma determinada região seja normalmente distribuída com média 
#de μ = 13 polegadas e desvio padrão de σ = 2 polegadas. Aproximadamente que porcentagem de plantas nesta região 
#tem entre 10 e 14 polegadas de altura? 
prob <- pnorm(14, mean = 13, sd = 2) - pnorm(10, mean = 13, sd = 2) 
prob


#DA UM VALOR A PARTIR DA DISTRIBUICAO NORMAL
qnorm(prob, mean = 0, sd = 1) 


#Rnorm - cria um vetor com uma distribuicao normal:
narrowDistribution <- rnorm(1000, mean = 50, sd = 15) 
hist(narrowDistribution)


#COMPARANDO CORRELACAO:
#===============================================================================

library(GGally)
ggpairs(captura)

library(corrplot)
corrplot(correlacao, method = "circle", type = "upper", 
         tl.col = "black", tl.srt = 45, 
         title = "Matriz de Correlação entre as Notas Finais", 
         mar = c(0, 0, 2, 0))

x <- c(2, 3, 3, 5, 6, 9, 14, 15, 19, 21, 22, 23) 
y <- c(23, 24, 24, 23, 17, 28, 38, 34, 35, 39, 41, 43) 
plot(x, y, pch=16)

#Nivel de correlacao:
cor.test(x, y) 

modelo<-lm(x~y)
abline(modelo)



#OUTLIERS:
#===============================================================================

#IIQ = 3°quartil − 1°quartil

Q1 <- quantile(dataset$NotaFinal, 0.25)  
Q3 <- quantile(dataset$NotaFinal, 0.75)  
IIQ <- Q3 - Q1

limite_inferior <- Q1 - 1.5 * IIQ
limite_superior <- Q3 + 1.5 * IIQ

#Neste caso quero os outliers abaixo do limite
outliers <- dataset[dataset$NotaFinal < limite_inferior, ]

#Z:
#é a posição do valor na distribuicao normal
  
#Z SCORE:
#quantos desvio padrao o valor ta acima ou abaixo da media
#-3,-2,-1,0,1,2,3
#acima de 2 ou abaixo de -2 sao considerados outliers

dados_raw <- data.frame("ra" = c("013215","013219","012306","023042", 
                                 "012326","012212","012320","013297", 
                                 "023207","013219","013207","013204"), 
                        "aluno" = c("ARCA","ACEM","NAYA","TOLS", 
                                    "YRIB","BASV","CEEA","KEAA", 
                                    "KEVE","KIAA","KEAN","LEAO"), 
                        "nota1" = c(7.25,1.84,8.40,9.50,0.00,6.92, 
                                    9.05,8.25,9.92,7.50,6.58,10.00), 
                        "nota2" = c(7.13,1.99,8.24,7.40,4.53,6.13, 
                                    9.4,0.00,10.00,6.03,7.40,8.40))

notas <- data.frame(dados_raw$nota1,dados_raw$nota2) 
zscore <- sapply(notas, function(notas) (notas-mean(notas))/sd(notas)) 
dados_t <- data.frame(dados_raw, zscore)
colnames(dados_t)[5] <- c("zscore1")
colnames(dados_t)[6] <- c("zscore2")


outliers_a <- dados_t[abs(dados_t$zscore1) > 2 | abs(dados_t$zscore2) > 2,] 
outliers_b <- dados_t[abs(dados_t$zscore1) > 3 | abs(dados_t$zscore2) > 3,] 
View(outliers_a) 
View(outliers_b)




#REGRA DE BAYES:
#A probabilidade condicional P(A∣B) mede a chance de A ocorrer sob a condição de que 𝐵 já ocorreu.
#A Regra de Bayes é uma ferramenta que usa probabilidades condicionais para calcular P(A∣B) em termos de P(B∣A), 𝑃(𝐴), e  𝑃(𝐵).











