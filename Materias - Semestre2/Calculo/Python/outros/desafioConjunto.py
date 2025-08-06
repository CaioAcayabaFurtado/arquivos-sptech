#Caio Acayaba Furtado, RA:03241026


#Parte - 2
portfolio_agora = {'ITSA4', 'ECOR3', 'TAEE11', 'B3SA3', 'VALE3'}
portfolio_ativa = {'B3SA3', 'BBDC4', 'BBSE3', 'BRDT3', 'TAEE11', 'TRPL4', 'VALE3', 'VIVT3'}
portfolio_genial = {'CPFE3', 'BEEF3', 'CYRE3', 'SAPT4', 'TRPL4', 'VIVT3'}
portfolio_easynvest = {'B3SA3', 'AGRO3', 'COCA34', 'TAEE11', 'VALE3', 'CPLE11', 'ITSA4', 'ABEV3', 'BBDC4', 'BBSE3', 'BRSR6', 'EGIE3', 'SAPR11', 'TRPL4', 'VIVT3'}
portfolio_elite = {'BBDC4', 'BBSE3', 'BRSR6', 'EGIE3', 'ITSA4', 'SANEPAR', 'TAEE11', 'TRPL4', 'VIVT3', 'VALE3'}
portfolio_guide = {'ALUP11', 'BBAS3', 'CYRE3', 'CPFE3', 'KLBN11', 'PSSA3', 'TIMS3', 'VALE3'}
portfolio_nova_futura = {'B3SA3', 'CYRE3', 'GGBR4', 'VIVT3', 'TRPL4'}
portfolio_orama = {'ABCB4', 'BBDC4', 'BEEF3', 'CESP6', 'EGIE3'}

print("Parte - 3 - Ações em comum de todas as corretoras:")
acaoComum = portfolio_agora & portfolio_ativa & portfolio_easynvest & portfolio_genial & portfolio_elite & portfolio_guide & portfolio_nova_futura & portfolio_orama
print(acaoComum)


print("\n")
print("\n")


print("Parte - 4 a) Ações em comum entre 4 corretoras")
acaoComum2 = portfolio_agora & portfolio_ativa & portfolio_easynvest & portfolio_elite 
print(acaoComum2)

print("\n")

print("Parte - 4 b) Ações diferentes entre 4 corretoras")

unica_agora = portfolio_agora - (portfolio_ativa | portfolio_easynvest | portfolio_elite)
print("Ações unicas da Agora:")
print(unica_agora)

unica_ativa = portfolio_ativa - (portfolio_agora | portfolio_easynvest | portfolio_elite)
print("Ações unicas da Ativa:")
print(unica_ativa)

unica_easynvest = portfolio_easynvest - (portfolio_ativa | portfolio_agora | portfolio_elite)
print("Ações unicas da Easynvest:")
print(unica_easynvest)

unica_elite = portfolio_elite - (portfolio_ativa | portfolio_easynvest | portfolio_agora)
print("Ações unicas da Elite:")
print(unica_elite)

print("\n")

print("Parte - 4 c) Determinar relações entre portifólios e corretoras")

agora_subset_ativa = portfolio_agora <= portfolio_ativa
agora_superset_ativa = portfolio_agora >= portfolio_ativa

elite_subset_easynvest = portfolio_elite <= portfolio_easynvest
elite_superset_easynvest = portfolio_elite >= portfolio_easynvest

print(agora_subset_ativa, agora_superset_ativa, elite_subset_easynvest, elite_superset_easynvest)

print("\n")

print("Parte - 4 c) Criar conjuntos de ações únicas de cada corretora")

print(unica_agora)
print(unica_ativa)
print(unica_easynvest)
print(unica_elite)




