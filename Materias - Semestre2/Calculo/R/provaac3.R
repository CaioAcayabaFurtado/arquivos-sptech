# Caio Acayaba Furtado
# RA: 03241026
# Código - AC3 



# ==============================================================================

# Q-1

#Sua empresa de entregas anuncia o envio de 90% de suas encomendas em três dias 
#úteis. A fim de verificar isso, você seleciona uma amostra aleatória simples 
#(AAS) de 100 das 5000 encomendas recebidas na semana passada. A auditoria revela 
#que 86 desses pedidos foram entregues a tempo.

#Se a companhia realmente entrega 90% das encomendas dentro do tempo anunciado, 
#qual a probabilidade de que 86 encomendas de 100 sejam entregues a tempo?

x = 86
size = 100
prob = 0.90
dbinom(x, size, prob) 



# ==============================================================================

# Q-2

#Nas fábricas, costumam-se selecionar alguns produtos finais com o propósito de 
#submetê-los a uma inspeção completa. Suponha que 8% de todos os produtos feitos 
#em uma determinada fábrica estejam fora das especificações. Além disso, 55% dos 
#itens fora das especificações são selecionados para passar por uma inspeção 
#completa, ao passo que 20% dos itens bons são selecionados para esse mesmo tipo 
#de inspeção mais detalhada. 
#
#Dado que um produto escolhido aleatoriamente passou 
#por uma inspeção completa, qual é a probabilidade de que ele esteja fora das 
#especificações?

# produtos bons = 92%
# produtos ruins = 8%

# 55% dos ruins vao para a inspecao
# 20% dos bons vao para a inspecao

x = 1
size = 100
prob = 0.55
dbinom(x, size, prob)




# ==============================================================================

# Q-3

#Um sujeito que acredita na teoria do "passeio aleatório" do mercado de capitais 
#pensa que vale 0,65 a probabilidade de que um determinado índice de ações suba 
#a cada ano (ou seja, 0,35 de não subir). Além disso, a variação do índice em um 
#ano qualquer não é influenciada pelo fato de o índice ter baixado ou subido nos 
#anos anteriores. Considere que X seja o número de anos em que haverá uma subida 
#do índice nos próximos 5 anos.

#Qual a probabilidade do índice de ações subir por no máximo 4 anos e no mínimo 
#2 anos?

x = 4
size = 5
prob = 0.65

#prob do maximo 4 anos
prob1 <- pbinom(x, size, prob) 
#prob de maximo 1 ano
x = 1
prob2 <- pbinom(x, size, prob) 

prob1
prob2

probFinal <- prob1 - prob2
probFinal




# ==============================================================================

# Q-4

#Uma empresa migrou seus servidores para a nuvem e está preocupada com o 
#dimensionamento adequado dos recursos para garantir o desempenho desejado do 
#sistema. A empresa observou que o tempo de resposta médio de suas instâncias de 
#servidor na nuvem segue uma distribuição normal com uma média de 100 
#milissegundos e um desvio padrão de 15 milissegundos.

#Qual é a probabilidade de uma instância de servidor ter um tempo de resposta 
#superior a 120 milissegundos? 90% das solicitações devem ser processadas em 
#menos de quanto tempo?

media_dados <- 100 
desvio_padrao_dados <- 15
pontuacao_corte <- 120 
probabilidade <- 1 - pnorm(pontuacao_corte, mean = media_dados, sd = desvio_padrao_dados) 
probabilidade


# ==============================================================================

# Q-5

#Uma equipe de operações de uma empresa que utiliza serviços em nuvem deseja 
#monitorar a utilização dos recursos para evitar possíveis problemas de 
#desempenho. A utilização do CPU em uma determinada instância de máquina 
#virtual segue uma distribuição normal com uma média de 60% e um desvio padrão 
#de 8%.

#Qual é a probabilidade de a utilização do CPU ser inferior a 70%, e se a 
#equipe deseja identificar os 10% dos casos com maior utilização de CPU, qual é 
#o limiar de utilização que deve ser considerado?

media_dados <- 60 
desvio_padrao_dados <- 8
pontuacao_corte <- 70
probabilidade <- pnorm(pontuacao_corte, mean = media_dados, sd = desvio_padrao_dados) 
probabilidade



# ==============================================================================
# ==============================================================================
# ==============================================================================



#Um grupo de indústrias alimentícias fez uma pesquisa em que pediu a 3368 
#pessoas que adivinhassem a quantidade de calorias existentes em cada um de 
#vários alimentos comuns. A tabela a seguir mostra a média dessas estimativas e 
#também os valores corretos das quantidades de calorias.


caloriasEstimadas <- c(196, 394, 350, 117, 136, 364, 74, 107, 160, 419)
caloriasEncontradas <- c(159, 163, 269, 61, 76, 260, 12, 80, 88, 160)

modeloCalorias <- lm(caloriasEncontradas~caloriasEstimadas)

#a) Acredita-se que a quantidade real de calorias contida em um alimento de fato 
#ajuda a explicar as estimativas que as pessoas fizeram sobre essa mesma medida.
#Com isso em mente, faça um diagrama de dispersão desses dados.

plot(
  caloriasEstimadas,
  caloriasEncontradas,
  xlab = "Calorias Estimadas", 
  ylab = "Calorias Encontradas", 
  col = "red", 
  pch = 16, 
  cex = 1 
)

#b) Calcule a correlação r. Explique a qualidade do valor de r encontrado.

correlacao <- cor.test(caloriasEstimadas, caloriasEncontradas)
correlacao

#O valor da correlação chegou a 0.82, logo ele é considerado uma correlação positiva e forte.


#c) As estimativas são todas maiores do que as quantidades reais de calorias. 
#Será que esse fato influencia a correlação de algum modo? De que forma r mudaria 
#se todas as estimativas tivessem 100 calorias a mais?

caloriasEstimadas2 <- c(296, 494, 450, 217, 236, 464, 174, 207, 260, 519)
plot(
  caloriasEstimadas2,
  caloriasEncontradas,
  xlab = "Calorias Estimadas", 
  ylab = "Calorias Encontradas", 
  col = "blue", 
  pch = 16, 
  cex = 1 
)
correlacao2 <- cor.test(caloriasEstimadas2, caloriasEncontradas)
correlacao2

  
#d) As estimativas são excessivamente altas para espaguete e bolos. Isole os 
#pontos correspondentes a estes dados utilizando sub-conjuntos. Calcule então r 
#para os alimentos restantes. Explique por que r mudou na direção observada.

caloriasEstimadasSubC <- c(394, 364, 419)
caloriasEncontradasSubC <- c(163, 260, 160)
plot(
  caloriasEstimadasSubC,
  caloriasEncontradasSubC,
  xlab = "Calorias Estimadas", 
  ylab = "Calorias Encontradas", 
  col = "blue", 
  pch = 16, 
  cex = 1 
)
correlacao3 <- cor.test(caloriasEstimadasSubC, caloriasEncontradasSubC)
correlacao3


#e) Calcule a reta de regressão que prevê as calorias encontradas com base nas 
#calorias estimadas. Trace a reta de regressão no gráfico de dispersão.

plot(
  caloriasEstimadas,
  caloriasEncontradas,
  xlab = "Calorias Estimadas", 
  ylab = "Calorias Encontradas", 
  col = "blue", 
  pch = 16, 
  cex = 1 
)

abline(modeloCalorias, col = "red", lwd = 2) 



