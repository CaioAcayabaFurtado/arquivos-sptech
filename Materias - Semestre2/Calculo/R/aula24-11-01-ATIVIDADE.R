
#Caio Acayaba Furtado, 03241026
#01/11/2024

# ATIVIDADE:
#Agora com a base base-r-wege-full separe o data frame em seções que fazem sentido para você, crie modelos de 
#regressão, busque relações entre as outras variáveis, altere variações de modelos para cada trecho no tempo. 
#Qual melhor modelo? Em que período? Quais insights podemos tirar dessa base de dados?

# Carregar a base de dados
acao <- read.csv("C:/Users/cacay/Documents/Git-Hub/Arquivos_Semestre2/R/csv/base-r-wege-full.csv")
acao$id <- seq(1:177)

# Carregar pacotes
library(GGally)
library(ggplot2)

# Análise de correlação para entender as relações entre as variáveis
correlacao <- cor(acao[, c("Open", "High", "Low", "Close")])
print(correlacao)

# Análise gráfica: explorando a relação entre as variáveis
ggpairs(acao[, c("Open", "High", "Low", "Close")])
# Podemos observar que os valores das colunas têm comportamento semelhante entre si

# Criando um modelo inicial com menor dispersão entre as variáveis
modelo1 <- lm(Open ~ Low, data = acao)
summary(modelo1)

# Gráfico para observar a dispersão dos dados em relação à reta de regressão do modelo inicial
ggplot(acao, aes(Open, Low)) +
  geom_point() +
  geom_smooth(method = "lm", formula = y ~ poly(x, 3)) +
  labs(title = "Dispersão entre Open e Low")

# Dividindo o data frame em duas partes
acaoPt1 <- subset(acao, id <= nrow(acao) / 2)
acaoPt2 <- subset(acao, id > nrow(acao) / 2)

# Gráficos de dispersão para cada parte
ggplot(acaoPt1, aes(Open, Low)) +
  geom_point() +
  geom_smooth(method = "lm", formula = y ~ poly(x, 3)) +
  labs(title = "Parte 1: Dispersão entre Open e Low")

ggplot(acaoPt2, aes(Open, Low)) +
  geom_point() +
  geom_smooth(method = "lm", formula = y ~ poly(x, 3)) +
  labs(title = "Parte 2: Dispersão entre Open e Low")

# Modelos de regressão separados para cada parte
modelo1.1 <- lm(Open ~ Low, data = acaoPt1)
summary(modelo1.1)

modelo1.2 <- lm(Open ~ Low, data = acaoPt2)
summary(modelo1.2)
