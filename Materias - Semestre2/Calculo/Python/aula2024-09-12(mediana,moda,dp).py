import statistics as st
set1 = [1, 3, 3, 4, 5, 7] 
set2 = [2, 3, 3, 4, 5, 5, 5, 5, 6, 6, 6, 7] 
set3 = [2.4, 1.3, 1.3, 1.3, 2.4, 4.6] 
set4 = ["red", "blue", "black", "blue", "black", "black", "brown"]
print("a mediana da base é {}".format(st.median(set1)))
print("a moda do data set 2 é % s" % (st.mode(set2)))
print("a moda do data set 3 é % s" % (st.mode(set3)))
print("a moda do data set 4 é % s" % (st.mode(set4)))


print("------------------------------------------------------------------------")


arr = [1, 2, 3, 4, 5, -2, -4, -3, -1, -5, -6, 1, 2, 5, 4, 8, 9, 12]
maximum = max(arr)
minimum = min(arr)
range = maximum - minimum
print(f'maximum = {maximum}, minimum = {minimum} e range = {range}')
print("Variância de arr é {:.02f}".format(st.variance(arr)))
print("O desvio padrão de arr é {:.02f}".format(st.stdev(arr)))
st.quantiles(arr, n=4, method='inclusive') 
#lembra da diferença entre inclusivo e exclusivo?
st.quantiles(arr, n=10, method='inclusive')
# Quais as vantagens e desvantagens de aumentar a minha divisão de amostra?