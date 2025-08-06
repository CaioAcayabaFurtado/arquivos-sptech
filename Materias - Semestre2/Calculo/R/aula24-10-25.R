#Dada a base prioridades-projeto-mestrado.xlsx no R:
  
#- No arquivo prioridades-projetos-mestrado.xlsx os respondentes priorizaram para 
#cada sub-area uma sugestão

prioridadesMestrado <-prioridades_projetos_mestrado
library(dplyr)

#- Como essas prioridades estão em escala likert também, mude as variáveis 
#categóricas para valores (Alta prioridade - 1, Baixa prioridade - 5)

prioridadesMestrado <- prioridadesMestrado %>%
  mutate_all(~ recode(., 
                      "Baixa prioridade" = 5,
                      "Média-baixa prioridade" = 4,
                      "Média prioridade" = 3,
                      "Alta-média prioridade" = 2,
                      "Alta prioridade" = 1))

#- Faça uma contagem para cada sugestão em cada categoria de prioridade

tabela<- lapply(prioridadesMestrado, table)
print(tabela)

#- Crie um gráfico barra para cada sub área com a contagem máxima de Alta 
#prioridade e Alta-média prioridade

doc1 <- (tabela$Doc1[1]+tabela$Doc1[2])
doc2 <- (tabela$Doc2[1]+tabela$Doc2[2])
doc3 <- (tabela$Doc3[1]+tabela$Doc3[2])
doc4 <- (tabela$Doc4[1]+tabela$Doc4[2])
doc5 <- (tabela$Doc5[1]+tabela$Doc5[2])
colunas<-c(doc1, doc2, doc3, doc4, doc5)
texto<-c("doc1", "doc2", "doc3", "doc4", "doc5")
gb<-barplot(colunas, names.arg = texto)
text(gb, colunas, labels = colunas, pos = 3, cex = 0.8)


#- Compare esses gráficos e analise qual sub área possuí sugestões com maiores 
#índices de prioridade 
print(colunas)

totalDOC<-sum(doc1, doc2, doc3, doc4, doc5)



