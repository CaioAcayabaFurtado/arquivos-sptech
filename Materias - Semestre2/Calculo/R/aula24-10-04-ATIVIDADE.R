#Caio Acayaba Furtado, 03241026

# LISTA DE EXERCÍCIO: HISTOGRAMA E BOXPLOT

#-----------------------------------------------------------

# 1. Você está realizando um experimento para comparar duas populações diferentes, cujos 
# pesos (em kg) seguem distribuições normais com médias e desvios padrão diferentes.

# a. Gere dois conjuntos de dados simulados com 100 observações cada, utilizando a 
# função rnorm. Defina a média do primeiro grupo como 70 e do segundo grupo 
# como 75, ambos com desvio padrão 12.
#dados simulados

n <- 100
mediaGrupo1 <- 70
mediaGrupo2 <- 75
desvioPadrao <- 12

set.seed(123)
gp1 <- rnorm(n, mediaGrupo1, desvioPadrao)
gp2 <- rnorm(n, mediaGrupo2, desvioPadrao)
summary(gp1)
summary(gp2)

# b. Construa um histograma para cada grupo e compare visualmente as distribuições.

par(mfrow=c(1,2))
hist(gp1, main='peso gp1', ylab='freq', xlab='peso')
hist(gp2, main='peso gp2', ylab='freq', xlab='peso')

# c. Gere um boxplot para cada grupo, visualizando a diferença nas medianas e a 
# dispersão dos dados.
boxplot(gp1)
boxplot(gp2)
boxplot(gp1, gp2)



# 2. Você está estudando a probabilidade de obter "cara" em lançamentos de uma moeda. 
# Utilize uma simulação binomial para 450 lançamentos de uma moeda justa (p = 0.5) e 
# outra moeda com probabilidade de 0.7 de sair "cara".

# a. Gere dois conjuntos de dados utilizando a função rbinom para simular os 
# resultados de 450 lançamentos de cada moeda.

n<-450
set.seed(123)
m1<-rbinom(n,1,0.5)
m2<-rbinom(n,1,0.7)

# b. Construa um histograma para cada conjunto de dados e analise a frequência de 
# "caras".

par(mfrow=c(1,2))
hist(m1, main='Prob.Cara moeda 1', ylab='freq', xlab='cara')
hist(m2, main='Prob.Cara moeda 2', ylab='freq', xlab='cara')

# c. Crie um boxplot para visualizar as distribuições dos resultados para ambas as 
# moedas.

boxplot(m1)
boxplot(m2)
boxplot(m1, m2)



# 3. Considere um cenário onde você está avaliando o desempenho de três grupos de 
# estudantes em uma prova. Suponha que as notas seguem uma distribuição normal.

# a. Utilize a função rnorm para simular as notas de 50 alunos em cada grupo. O grupo 1 
# deve ter uma média de 60 com desvio padrão 15, o grupo 2 uma média de 75 com 
# desvio padrão 10 e o grupo 3 uma média de 55 com desvio padrão de 17.

n <- 50
mediaGrupo1 <- 60
mediaGrupo2 <- 75
mediaGrupo3 <- 55
desvioPadrao1 <- 15
desvioPadrao2 <- 10
desvioPadrao3 <- 17

set.seed(123)
gp1 <- rnorm(n, mediaGrupo1, desvioPadrao1)
summary(gp1)
gp2 <- rnorm(n, mediaGrupo2, desvioPadrao2)
summary(gp2)
gp3 <- rnorm(n, mediaGrupo3, desvioPadrao3)
summary(gp3)

# b. Gere um histograma para cada grupo e compare as distribuições das notas.

hist(gp1)
hist(gp2)
hist(gp3)

# c. Crie boxplots para observar a dispersão e possíveis outliers em ambos os grupos.

boxplot(gp1)
boxplot(gp2)
boxplot(gp3)
boxplot(gp1, gp2, gp3)



# 4. Em uma fábrica, duas máquinas diferentes produzem peças. A produção diária de cada 
# máquina segue uma distribuição binomial, onde a máquina 1 tem uma probabilidade de 
# sucesso de 0.6 e a máquina 2 de 0.8. Cada máquina realiza 100 operações por dia.

# a. Simule os resultados de produção de ambas as máquinas durante 50 dias, 
# utilizando a função rbinom.

n_dias <- 50
n_operacoes <- 100

set.seed(123)
maquina1 <- rbinom(n_dias, n_operacoes, 0.6)
summary(maquina1)
maquina2 <- rbinom(n_dias, n_operacoes, 0.8)
summary(maquina2)

# b. Construa um histograma para visualizar a produção de cada máquina.

hist(maquina1)
hist(maquina2)

# c. Gere boxplots para comparar a produção diária das duas máquinas

boxplot(maquina1)
boxplot(maquina2)
boxplot(maquina1, maquina2)
