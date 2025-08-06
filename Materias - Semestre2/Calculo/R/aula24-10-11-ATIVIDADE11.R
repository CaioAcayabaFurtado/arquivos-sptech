#LISTA DE EXERCÍCIOS

#1
#a
n <- 20000
set.seed(1234)
idade <- abs(rnorm(n, 35, 7))
summary(idade)

#b
hist(idade,
     main = "histograma da idade",
     ylab = "frequência",
     xlab = "idade (em anos completos)"
)

#c
summary(round(idade), 0)

#2
seed <- 20
set.seed(seed)
notas <- data.frame(Matricula = sample(x = 011475:012990, size = 30, replace = FALSE),
                    ac1 = sample(x = 1:10, size = 30, replace = TRUE),
                    ac2 = sample(x = 1:10, size = 30, replace = TRUE),
                    prova_final= sample(x = 1:10, size = 30, replace = TRUE))

data.frame(notas)
resultado <- vector()

for (i in 1:nrow(notas)) {
  AC1 <- notas[i, "ac1"] * 0.4
  AC2 <- notas[i, "ac2"] * 0.4
  pFinal <- notas[i, "prova_final"] * 0.2
  notaFinal <- AC1 + AC2 + pFinal
  
  if (notaFinal >= 7) {
    resultado[i] <- "Aprovado"
  } else {
    resultado[i] <- "Reprovado"
  }
}
notas$resultado <- resultado
sample(notas)
summary(round(notas), 0)

#3
quadro1 <- data.frame(
  vendedor = c("Ana", "Flavia", "Pedro", "Mariana", "Ana", "Flavia", "Pedro", "Mariana"),
  dia = c(1, 1, 1, 1, 2, 2, 2, 2),
  produto = c("saia", "casaco", "vestido", "meia",
              "calçaFlaire", "calçaSkinny", "calçaSkinny", "manta"),
  quantidade = c(1, 2, 1, 5, 3, 0, 3, 4)
)

quadro2 <- data.frame(
  produto = c("calça", "vestido", "blusa", "manta", "saia", "casaco", "meia"),
  precoUnitario = c(240, 320, 98, 69, 145, 289, 39),
  custoUnitario = c(210, 160, 60, 39, 89, 144, 15)
)

dadosCombinados <- merge(quadro1, quadro2, by = "produto")

dadosCombinados$faturamento <- dadosCombinados$quantidade * dadosCombinados$precoUnitario

faturamentoPorVendedor <- aggregate(faturamento ~ vendedor, data = dadosCombinados, sum)

barplot(faturamentoPorVendedor$faturamento, 
        names.arg = faturamentoPorVendedor$vendedor,
        main = "Faturamento por Vendedor",
        xlab = "Vendedor",
        ylab = "Faturamento")

#4
ex4 <- function() {
  num1 <- readline(prompt = "Digite um número: ")
  num2 <- readline(prompt = "Digite um número: ")
  
  while (is.na(as.numeric(num1)) || is.na(as.numeric(num2))) {
    print("Insira apenas valores numéricos!")
    num1 <- readline(prompt = "Digite um número: ")
    num2 <- readline(prompt = "Digite um número: ")
  }
  
  num1 <- as.numeric(num1)
  num2 <- as.numeric(num2)
  
  maior <- max(num1, num2)
  print(paste("Maior valor:", maior))
}

ex4()

#5
seed <- 10
set.seed(seed)
vetorNumerico <- sample(x = 1:25, size = 800, replace = TRUE)

histograma <- hist(vetorNumerico)
freq <- histograma$counts
print(freq)

#6
set.seed(50)
vendas <- data.frame(Qtd = sample(x = 28:52, size = 30, replace = TRUE),
                     DiaMes = seq(from=as.Date("2021-09-01"), to=as.Date("2021-09-30"), by="day"),
                     DiaSemana = weekdays(seq(from=as.Date("2021-09-01"), to=as.Date("2021-09-30"),
                     by="day")))

plot(vendas$DiaMes, vendas$Qtd, main = "Frequência da Qtd de vendas",
     xlab = "Dia", 
     ylab = "Qtd vendas")
varmes <- max(vendas$Qtd) - min(vendas$Qtd)
print(varmes)


medven<- round(mean(vendas$Qtd))
print(medven)

hist(vendas$Qtd, main = "Frequência da Qtd de vendas",xlab = "Qtd vendas", 
     ylab = "Frequência", col = "green")