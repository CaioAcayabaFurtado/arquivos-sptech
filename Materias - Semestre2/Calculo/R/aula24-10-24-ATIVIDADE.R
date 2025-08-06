#TRATAMENTO DE DADOS: PERGUNTAS MESTRADO
perguntaMestrado <-perguntas_mestrado
library(dplyr)

#- limpeza da base perguntas-mestrado.xlsx com as remoções dos NAs (você decide se vai ser por registro ou por coluna)
perguntaMestrado[perguntaMestrado == "NA"] <- NA

#- Como a pesquisa está numa escala tipo likert, mude as variáveis categóricas para valores (Concordo totalmente - 5, Discordo totalmente - 1)
perguntaMestrado <- perguntaMestrado %>%
  mutate_all(~ recode(., 
                      "Concordo totalmente" = 5,
                      "Concordo" = 4,
                      "Não concordo e nem discordo" = 3,
                      "Discordo" = 2,
                      "Discordo totalmente" = 1))

#- As perguntas estão separadas por setor, Q_1.1 Q_1.2 por exemplo são do setor 1, crie novas colunas scores, como por exemplo score_1 para cada setor
perguntaMestrado$scoreQ1 <- rowMeans(perguntaMestrado[, c("Q_1.1", "Q_1.2")])
perguntaMestrado$scoreQ2 <- rowMeans(perguntaMestrado[, c("Q_2.1", "Q_2.2", "Q_2.3")])
perguntaMestrado$scoreQ3 <- rowMeans(perguntaMestrado[, c("Q_3.1", "Q_3.2", "Q_3.3")])
perguntaMestrado$scoreQ4 <- rowMeans(perguntaMestrado[, c("Q_4.1", "Q_4.2", "Q_4.3", "Q_4.5")])
perguntaMestrado$scoreQ5 <- rowMeans(perguntaMestrado[, c("Q_5.1", "Q_5.2", "Q_5.4")])
perguntaMestrado$scoreQ6 <- rowMeans(perguntaMestrado[, c("Q_6.1", "Q_6.2", "Q_6.3")])
perguntaMestrado$scoreQ7 <- rowMeans(perguntaMestrado[, c("Q_7.1", "Q_7.2", "Q_7.3")])
perguntaMestrado$scoreQ8 <- rowMeans(perguntaMestrado[, c("Q_8.1", "Q_8.2", "Q_8.3", "Q_8.4", "Q_8.5")])
perguntaMestrado$scoreQ9 <- rowMeans(perguntaMestrado[, c("Q_9.1", "Q_9.2", "Q_9.3")])

#- Nos scores criados anteriormente calcule a média para cada score por área. Por exemplo: score_1 = média entre Q_1.1 e Q_1.2
mediaQ1<-mean(perguntaMestrado$scoreQ1)
mediaQ2<-mean(perguntaMestrado$scoreQ2)
mediaQ3<-mean(perguntaMestrado$scoreQ3)
mediaQ4<-mean(perguntaMestrado$scoreQ4)
mediaQ5<-mean(perguntaMestrado$scoreQ5)
mediaQ6<-mean(perguntaMestrado$scoreQ6)
mediaQ7<-mean(perguntaMestrado$scoreQ7)
mediaQ8<-mean(perguntaMestrado$scoreQ8)
mediaQ9<-mean(perguntaMestrado$scoreQ9)
mediaTotal<- c(mediaQ1, mediaQ2, mediaQ3, mediaQ4, mediaQ5, mediaQ6, mediaQ7, mediaQ8, mediaQ9)

#- Crie um gráfico barra para representar a média de cada score
nomeColuna <- c("MediaQ1", "MediaQ2", "MediaQ3", "MediaQ4", "MediaQ5", "MediaQ6", "MediaQ7", "MediaQ8", "MediaQ9")
barplot(mediaTotal, names.arg = nomeColuna)

#- Analise esse gráfico, comentando sobre os valores encontrados, o que eles podem representar e possíveis implicações
#De acordo com as respostas, em média a escala está acima de 3, ou seja, 
#grande maioria das pessoas que responderam as perguntas, ou não concordam 
#nem discordam, ou concordam.

