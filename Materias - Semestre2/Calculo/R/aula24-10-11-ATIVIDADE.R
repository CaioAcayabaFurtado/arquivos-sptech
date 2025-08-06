

#1) A linguagem R tem uma especificidade de aplicações: 
#Justifique por que é aplicada para

#simulação de dados:
n <- 100
usoCPU <- runif(n, min = 0, max = 1)

#a) Aplicações estatísticas. Dê um exemplo prático
  #R é amplamente reconhecido como uma linguagem poderosa para aplicações 
  #estatísticas permitindo calcular métricas como média, mediana, variância, 
  #além de realizar testes de hipóteses e ajustes de modelos estatísticos. 
  summary(usoCPU)
  
#b) Criação de visualizações gráficas. Dê um exemplo prático. 
    #R facilita a visualização de padrões e tendências nos dados, permitindo 
    #gráficos como histogramas e boxplots, sendo fundamental para comunicar 
    #insights de forma clara e visual.
    hist(usoCPU)
  
  
#c) Implementação de modelos matemáticos. Dê um exemplo de modelagem 
#matemática na prática. 
  #Utilizando uma base de dados simulados, com o uso de operações matemáticas, 
  #podemos melhorar a visualização dos dados. Essa simples transformação pode 
  #ser aplicada em diversos contextos de modelagem
  summary(round(usoCPU*100,1))


#----------------------------------------------------------------------------------------------


#2) Em um contexto de cursos superior de bacharelado, um sistema está sendo criado para 
#se tornar inteligente e apontar o desempenho dos alunos. Uma prova de conceito (POC) 
#é realizada à partir do trecho de código e dos seguintes requisitos a seguir. Apresente 
#os resultados dos alunos aprovados em forma gráfica obedecendo ao código e 
#requisitos. Faça insights. 

#a) Trecho de código
seed=20
set.seed(seed)
notas <- data.frame(Matricula = sample(x = 011475:012990, size = 30, replace = FALSE),
                    ac1 = sample(x = 1:10, size = 30, replace = TRUE),
                    ac2 = sample(x = 1:10, size = 30, replace = TRUE),
                    prova_final= sample(x = 1:10, size = 30, replace = TRUE))
#b) Requisitos: 
  data.frame(notas)#é uma função no R que cria uma tabela ou array de 2 dimensões. 
  # sample() (é uma função que retira uma amostra do conjunto de dados, quando a função replace = TRUE 
         #a amostra tem substituição)
#Pesos das avaliações: 
  #ac1 = 40%
  #ac2 = 40%
  #Prova_final = 20%
#A aprovação é quando a média de ac1, ac2 e prova_final >= 7
  

resultado<-vector()
notaFinalLista<-vector()

for (i in 1:nrow(notas)) {
  AC1 <- notas[i, "ac1"] * 0.4
  AC2 <- notas[i, "ac2"] * 0.4
  PFinal <- notas[i, "prova_final"] * 0.2
  notaFinal <- AC1 + AC2 + PFinal
  if(notaFinal >= 7){
    resultado[i] <- "aprovado"
    notaFinalLista[i]<-notaFinal
  } else {
    resultado[i] <- "reprovado"
    notaFinalLista[i]<-notaFinal
  }
}
print(resultado)
notas$resultado <- resultado
notas$notaFinal <- notaFinalLista
print(notas$resultado, notas$notaFinal)
print(notaFinalLista)
hist(notaFinalLista)

  

#----------------------------------------------------------------------------------------------
  
  
#3) Dados dois quadros, análise e utilize o quadro 2 para criar colunas novas no quadro 1. E plote uma 
#representação gráfica de barras do faturamento de cada vendedor. Faça insights

quadro1 <- data.frame(vendedor = c("Ana", "Flavia", "Pedro", "Mariana", "Ana", "Flavia", "Pedro", "Mariana"),
                      dia = c(1, 1, 1, 1, 2, 2, 2, 2),
                      produto = c("saia", "casaco", "vestido", "meia", "calçaFlaire", "calçaSkinny", "calçaSkinny", "manta"),
                      quantidade = c(1, 2, 1, 5, 3, 0, 3, 4)
                      )

quadro2 <- data.frame(produto = c("calça", "vestido", "blusa", "manta", "saia", "casaco", "meia"),
                      precoUnitario = c(240, 320, 98, 69, 145, 289, 39),
                      custoUnitario = c(210, 160, 60, 39, 89, 144, 15)
                      )

quadroCombinado <- merge(quadro1, quadro2, by = "produto")

quadroCombinado$faturamento <- (quadroCombinado$quantidade * quadroCombinado$precoUnitario)

faturamentoVendedor <- aggregate(faturamento ~ vendedor, data = quadroCombinado, sum)

barplot(faturamentoVendedor$faturamento, 
        names.arg = faturamentoVendedor$vendedor,
        ylab = "Faturamento")



#----------------------------------------------------------------------------------------------


#4) Crie o seguinte código em R:
#Dados dois valores, retorna o maior entre eles. Obs.: Insira mensagens de aviso para o usuário caso o 
#valor que ele insira não seja numérico.

at4 <- function() {
  valor1 <- readline(prompt="Insira o primeiro número: ")
  valor1 <- as.numeric(valor1)
  
  if (is.na(valor1)) {
    print("O valor inserido não é numérico. Por favor, insira um número.")
    at4()
    break
  }
  
  valor2 <- readline(prompt="Insira o segundo número: ")
  valor2 <- as.numeric(valor2)
  
  if (is.na(valor2)) {
    print("O valor inserido não é numérico. Por favor, insira um número.")
    at4()
    break
  }
  
  # Comparar os valores e retornar o maior
  if (valor1 > valor2) {
    print(paste("O maior valor é:", valor1))
  } else if (valor1 == valor2) {
    print("Os valores são iguais.")
  } else {
    print(paste("O maior valor é:", valor2))
  }
}

at4()


#----------------------------------------------------------------------------------------------


#5) Utilizando seed =10, crie um vetor de valores numéricos com o seguinte código:
  set.seed(10)
  vetorNumerico <- sample(x = 1:25, size = 800, replace = TRUE)
#Calcule a frequência em que cada número aparece.
#Lembre-se sample é uma função em R que gera uma amostra de dados. Consulte documentação
#(https://www.rdocumentation.org/packages/base/versions/3.6.2/topics/sample).
  
  histograma<-hist(vetorNumerico)
  frequencias<-histograma$counts
  print(frequencias)


#----------------------------------------------------------------------------------------------


#6) Para estudar o comportamento das vendas, um administrador coleta informações sobre o número de 
#itens vendidos nos últimos 30 dias. Simule a coleta destes dados, onde as vendas variem entre 28 e 52 
#unidades. Use set.seed(50), de forma que possamos reproduzir os resultados dos chamados de Geradores 
#de Números Aleatórios. Estes são, na verdade, Pseudo-Aleatórios porque são totalmente algorítmicos: 
#dada a mesma semente, você obtém a mesma sequência.

vendas <- data.frame(Qtd = sample(x = 28:52, size = 30, replace = TRUE),
                     DiaMes = seq(from=as.Date("2021-09-01"), to=as.Date("2021-09-30"), by="day"),
                     DiaSemana = weekdays(seq(from=as.Date("2021-09-01"), to=as.Date("2021-09-30"), 
                     by="day")))

#a) Plote um gráfico de dispersão da quantidade de vendas por dia.
plot(vendas$DiaMes, vendas$Qtd, main = "Frequência da Qtd de vendas",xlab = "Dia", ylab = "Qtd vendas")

#b) Qual foi a variação das vendas no mês de análise?
varPorMes <- max(vendas$Qtd) - min(vendas$Qtd)
print(varPorMes)  

#c) Qual é a média de quantidade de vendas?
medVendas<- round(mean(vendas$Qtd))
print(medVendas)
  
#d) Construa o histograma da frequência de vendas. Coloque o título e os rótulos nos eixos e determine 
#uma cor para o histograma.
hist(vendas$Qtd, main = "Frequência da Qtd de vendas",xlab = "Qtd vendas", 
     ylab = "Frequência", col = "green")

#Você deverá analisar e justificar os itens a,b,c e d. 
#observando o gráfico de dispersão e o histograma, percebemos que a quantidade de vendas por dia varia muito, entretanto, a média obtida junto com a variação no mês faz sentido quando analisamos todos os elementos. 

#----------------------------------------------------------------------------------------------


#7) Gere as seguintes sequências e atribua a uma variável:
  #a) x1 = 1, 2, 3, 4
  x1 <- c(1, 2, 3, 4)
  #b) x2 = 2, 5, 8, 11
  x2 <- c(2, 5, 8, 11)
  #c) x3 = A, B, C, D
  x3 <- c("A", "B", "C", "D")
  #d) x4 = a, b, c, d
  x4 <- c("a", "b", "c", "d")
  #e) x5 = A, C, E, G
  x5 <- c("A", "C", "E", "G")
  #f) x6 = 10, 9, 8, 7
  x6 <- c(10, 9, 8, 7)
  #g) x7 = 10, 8, 6, 4
  x7 <- c(10, 8, 6, 4)
  #h) x8 = 2, 2, 2, 2, 5, 5, 5, 5, 8, 8, 8, 8
  x8 <- c(2, 2, 2, 2, 5, 5, 5, 5, 8, 8, 8, 8)
  #i) x9 = recebe todas as sequências anteriores (x1, x2, x3, x4, x5, x6, x7, x8)
  x9 <- c(x1, x2, x3, x4, x5, x6, x7, x8)
  
  
#----------------------------------------------------------------------------------------------
  
  
#8) Atribua a data de hoje para a variável x e a data do seu nascimento para a variável y e verifique a sua 
#idade em dias. Verifique se sua idade está correta em anos.
  
  dataAtual <- Sys.Date()
  print(dataAtual)
  aniversario <- as.Date("2006-02-14")
  idade<- 18
  
  idadeDias<-as.numeric(dataAtual-aniversario)
  print(idadeDias)
  idadeEstimada<-round(idadeDias/365.25, 1)
  print(idadeEstimada)
  


#----------------------------------------------------------------------------------------------


#9) Dados dois quadros, análise e utilize o quadro 2 para criar colunas novas no quadro 1. E plote uma 
#representação gráfica de barras do faturamento de cada vendedor. Faça insights
  
  quadro1 <- data.frame(
    vendedor = c("Ana", "Flavia", "Pedro", "Mariana", "Ana", "Flavia", "Pedro", "Mariana"),
    dia = c(1, 1, 1, 1, 2, 2, 2, 2),
    produto = c("saia", "casaco", "vestido", "meia", "calçaFlaire", "calçaSkinny", "calçaSkinny", "manta"),
    quantidade = c(1, 2, 1, 5, 3, 0, 3, 4)
  )
  
  quadro2 <- data.frame(
    produto = c("calça", "vestido", "blusa", "manta", "saia", "casaco", "meia"),
    precoUnitario = c(240, 320, 98, 69, 145, 289, 39),
    custoUnitario = c(210, 160, 60, 39, 89, 144, 15)
  )
  
  quadroCombinado <- merge(quadro1, quadro2, by = "produto")
  
  quadroCombinado$faturamento <- (quadroCombinado$quantidade * quadroCombinado$precoUnitario)
  
  faturamentoVendedor <- aggregate(faturamento ~ vendedor, data = quadroCombinado, sum)
  
  barplot(faturamentoVendedor$faturamento, 
          names.arg = faturamentoVendedor$vendedor,
          ylab = "Faturamento")
  
#a) Quantas calças (flaire + skinny) foram vendidas? Qual o Faturamento total em calças?
  calcasVendidas <- quadro1[grepl("calça", quadro1$produto), ]
  print(sum(calcasVendidas$quantidade))

  
#b) Qual vendedor obteve maior desempenho de vendas (maior faturamento)? E qual vendedor obteve 
#menor desempenho?
  #Analisando o gráfico de barras, quem faturou mais foi a Flavia, já quem teve o pior desempenho foi a Ana
  
#c) Qual faturamento gerado pelo vendedor Pedro?
  fatPedro <- quadroCombinado[grepl("Pedro", quadroCombinado$vendedor), ]
  print(fatPedro$faturamento)
  

#----------------------------------------------------------------------------------------------

#10) Para estudar o desempenho dos vendedores, o gerente de uma loja de vestuário coleta informações sobre 
#o número de peças vendidas por cada vendedor nos últimos 30 dias. Simule a coleta destes dados, conforme 
#as instruções:
  
#a) Use set.seed(20);
set.seed(20)

#b) os vendedores são: Ana, Flavia, Pedro e Mariana. Cada vendedor deve apresentar dados referentes às 
#suas vendas para os 30 dias; (Dica: use a função rep() e a cada 4 elementos repita a lista de vendedores; 
#Faça o vetor dias ter tamanho 30*4);
  
vendedores <- rep(c("Ana", "Flavia", "Pedro", "Mariana"), times = 30)
  
#c) os produtos comercializados são: calçaFlaire, calçaSkinny, vestido, blusa, manta, saia, casaco, meia;
#as vendas devem variar entre 0 e 5 unidades diárias;

produtos <- c("calçaFlaire", "calçaSkinny", "vestido", "blusa", "manta", "saia", "casaco", "meia")

vendas<- data.frame(dias=rep(1:30, each=4),
                    vendedor=vendedores,
                    produto=sample(produtos, size=30*4, replace = TRUE),
                    unidadeVendas=sample(0:5, size=30*4, replace = TRUE))
print(vendas)
#----------------------------------------------------------------------------------------------
  
  
#11) Crie as seguintes funções:
#Dado um vetor, retorna um novo vetor de somas acumuladas a cada posição.
#Exemplo: Entrada: x <- c(1,3,8,2,1,3) ; Saída: 1, 4, 12, 14, 15, 18.

at11 <- function(){
  
  n <- 6
  x <- round(runif(n, min = 0, max = 10),0)
  y <- numeric(length(x)) 
  y[1] <- x[1] 
  
  for (i in 2:length(x)) {
    y[i] <- y[i - 1] + x[i] 
  }
  print(x)
  print(y)
}
at11()

#----------------------------------------------------------------------------------------------


#12) Projetinho: Dada a base de dados de temperatura das cidades do mundo, com o arquivo em csv, aplique 
#técnicas de ETL e sumarizações para criar uma nova tabela que apresente a média do último ano de coleta 
#das temperaturas de cada cidade. Utilize as ferramentas que você domina. Feito isso, apresente um gráfico 
#das cidades que tem as 5 maiores temperatura mundiais. 

#Avalie se vc precisa tratar os dados, limpar, excluir, para que surjam novas tabelas, mais reduzidas, de modo 
#a facilitar os insights. Mostre com prints de tela todas fases do projetinho.

#Alterando nome das colunas
colnames(city_temperature) <- c("continente", "pais", "estado", "cidade", "mes", "dia", "ano", "temperatura")
colnames(city_temperature)

#Formatando valores nulos
city_temperature[city_temperature == ""] <- NA

#Removendo linhas vazias
city_temperature <- city_temperature[rowSums(is.na(city_temperature)) != ncol(city_temperature), ]

#Removendo linhas duplicadas
city_temperature <- unique(city_temperature)

#Dados gerais de temperatura
summary(city_temperature$temperatura)

install.packages("dplyr") 

mediaCidadeAnual <- city_temperature %>% group_by(cidade, ano)

#ajuste de classe da coluna temperatura
city_temperature$temperatura <- as.numeric(city_temperature$temperatura)

#média de temperatura do ultimo ano
dados2020<- city_temperature[grepl("2020", city_temperature$ano), ]
print(dados2020)
media2020<-round(mean(dados2020$temperatura),1)
print(media2020)

library(dplyr)

#média de temperatura por cidade
media_por_cidade <- city_temperature %>% group_by(cidade) %>% summarize(temperatura_media = mean(temperatura))
print(media_por_cidade)
hist(media_por_cidade$temperatura_media)

#ordenar cidades por ordem decrecente (temperatiura)
media_por_cidade <- media_por_cidade %>% arrange(desc(temperatura_media))

#top 5 cidades

top5 <- head(media_por_cidade,5)
top5$temperatura_media <- as.numeric(top5$temperatura_media)
top5
barplot(top5$temperatura_media, 
        names.arg = top5$cidade)






