
# 21 - 11 - 2024

#Diversos fundos mútuos comparam seu próprio desempenho com o de uma referência (benchmark), um índice dos 
#retornos de todos os papeis que esse tipo de fundo compra. Por exemplo, o Vanguard International Growth Fund 
#estabelece como uma referência, o índice Morgan Stanley para a Europa, a Austrália e o Extremo Oriente (EAFE
#Europe, Australia and Far East), que mede o desempenho das ações fora dos Estados Unidos. Apresentam-se a seguir 
#os retornos percentuais do fundo em comparação com os do índice EAFE para o período entre 1982 até 2000. 


ano <- c(1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 
         1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000)

fundo <- c(5.27, 43.08, -1.02, 56.94, 56.71, 12.48, 11.61, 27.76, 
           -12.05, 4.74, -5.79, 44.74, 0.76, 14.89, 14.65, 4.12, 
           16.93, 26.34, -8.60)

eafe <- c(-0.86, 24.61, 7.86, 56.72, 69.94, 24.93, 28.59, 10.80, 
          -23.20, 12.50, -11.85, 32.94, 8.06, 11.55, 6.36, 2.06, 
          20.33,27.30,-13.96)

#Faça um diagrama de dispersão adequado para fazer previsões dos rendimentos do fundo baseando-se nos 
#rendimentos do EAFE. Nesse diagrama, existe um padrão bem definido de linha reta? Quão forte é esse padrão 
#(forneça uma medida numérica)? Há nele outliers em relação ao padrão linear? 


plot(eafe, fundo, 
     main = "Diagrama de Dispersão",
     xlab = "EAFE (%)",
     ylab = "Fundo (%)",
     pch = 19, col = "black")

regressao <- lm(fundo ~ eafe)
abline(regressao, col = "blue", lwd = 2)

correlacao <- cor(eafe, fundo)
cat("Correlação:", correlacao)

residuos <- rstandard(regressao)
outliers <- which(abs(residuos) > 2)
cat("Outlier detectado no ano de ", ano[outliers])