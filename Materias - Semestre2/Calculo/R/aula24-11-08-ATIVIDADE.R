#Caio Acayaba Furtado, 03241026


#ANOTAÇÕES:
#Quando a ordem importa (Arranjo ou Permutação)
#Quando a ordem NÃO importa (Combinação)
# - Ordem importa: As posições são importantes; AB é diferente de BA.
# - Ordem não importa: As posições não importam; AB é igual a BA.



#COMANDOS PARA: ----------------------------------------------------------------



# Permutação = combinação com todos os elementos.
factorial(5)
# A Permutação sempre é uma Arranjo, porém com a qtdDigitos = n



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



#ATIVIDADE: PROBABILIDADE ------------------------------------------------------



#1. Você é um administrador de um data center e deseja implantar 5 servidores em diferentes locais geográficos 
#para garantir alta disponibilidade. Quantas maneiras diferentes existem de escolher a ordem de implantação 
#dos servidores? 
factorial(5)
#RESPOSTA: Utilizando o calculo fatorial percebemos que existem 120 tipos de combinações diferentes de ordem 
#para a implementação.



#2. Suponha que você esteja gerenciando um fundo de investimento e tem 10 ações em que pode investir. Você 
#deseja criar uma carteira de investimentos com 3 ações escolhidas a partir dessas 10. Quantas combinações 
#de carteiras diferentes você pode criar? 
acoes <- 10
carteiras <- 3
combinacoes <- choose(acoes, carteiras)
#RESPOSTA: Existem 120 tipos de combinações diferentes de carteiras para criar.

  

#3. Você está configurando uma rede de entrega de conteúdo (CDN) para seu aplicativo web e tem 5 servidores 
#disponíveis em diferentes regiões. Você deseja escolher 2 servidores para configurar o CDN. Quantas 
#combinações diferentes de servidores você pode escolher para o CDN? 
servidoresDisponiveis <- 5
escolhaServidores<- 3
combinacoes2 <- choose(servidoresDisponiveis, escolhaServidores)
#RESPOSTA: Existem 10 tipos de combinações diferentes de servidores.



#4. Você está planejando alocar tarefas em um cluster de servidores. Você tem 4 servidores disponíveis e 6 
#tarefas a serem alocadas. Quantas permutações diferentes de alocação de tarefas são possíveis? 
calcularArranjoTarefas <- function(n, r){ 
  if (n < r){ 
    return("N menor que 0")
  } else { 
    return(factorial(n) / factorial(n - r)) 
  }
} 
tarefas <- 6 
servidoresDisponiveis2 <- 4 
arranjos <- calcularArranjoTarefas(tarefas, servidoresDisponiveis2)
arranjos
#RESPOSTA: Existem 360 permutações diferentes de alocação de tarefas.



#5. Você está gerenciando um projeto de desenvolvimento de software e precisa alocar 3 desenvolvedores de 
#uma equipe de 6 para uma tarefa específica. Quantas combinações diferentes de equipes de 
#desenvolvedores você pode formar?
qtdDev <- 6
escolhaDev<- 3
combinacoes3 <- choose(qtdDev, escolhaDev)
#RESPOSTA: Existem 20 tipos de combinações diferentes de trios para a atividade.





















