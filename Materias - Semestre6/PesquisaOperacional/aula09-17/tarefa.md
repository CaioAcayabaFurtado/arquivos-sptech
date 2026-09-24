# Resolução detalhada — Problema de Transporte

## Critério do MODI

Para cada célula básica, calcula-se:

\[
u_i + v_j = c_{ij}
\]

Para as células não básicas:

\[
\Delta_{ij} = c_{ij} - (u_i + v_j)
\]

Se existir algum \(\Delta_{ij} < 0\), a solução pode ser melhorada. Quando todos os custos reduzidos forem maiores ou iguais a zero, a solução é ótima.

---

# Exercício 1 — Centros de distribuição

## Dados

| Origem/Destino | Região 1 | Região 2 | Região 3 | Oferta |
|---|---:|---:|---:|---:|
| CD Alfa | 7 | 9 | 4 | 60 |
| CD Beta | 8 | 5 | 6 | 50 |
| Demanda | 40 | 45 | 25 | 110 |

Oferta total = 110  
Demanda total = 110  
Problema balanceado.

## 1. Solução inicial pelo Canto Noroeste

1. CD Alfa → Região 1: \(\min(60,40)=40\)
2. CD Alfa → Região 2: \(\min(20,45)=20\)
3. CD Beta → Região 2: \(\min(50,25)=25\)
4. CD Beta → Região 3: \(\min(25,25)=25\)

| Origem/Destino | Região 1 | Região 2 | Região 3 | Oferta |
|---|---:|---:|---:|---:|
| CD Alfa | 40 | 20 | 0 | 60 |
| CD Beta | 0 | 25 | 25 | 50 |
| Demanda | 40 | 45 | 25 | 110 |

Custo inicial:

\[
Z = 40(7) + 20(9) + 25(5) + 25(6)
\]

\[
Z = 280 + 180 + 125 + 150 = \boxed{R\$ 735}
\]

## 2. MODI — Iteração 1

Células básicas: Alfa-1, Alfa-2, Beta-2 e Beta-3.

Definindo \(u_{\text{Alfa}}=0\):

\[
v_1=7
\]

\[
v_2=9
\]

\[
u_{\text{Beta}}=5-9=-4
\]

\[
v_3=6-(-4)=10
\]

Custos reduzidos das células não básicas:

| Célula | Cálculo | \(\Delta\) |
|---|---|---:|
| Alfa → Região 3 | \(4-(0+10)\) | -6 |
| Beta → Região 1 | \(8-(-4+7)\) | 5 |

Como Alfa → Região 3 possui custo reduzido negativo, ela entra na solução.

Ciclo de ajuste:

```text
Alfa-3 (+) → Alfa-2 (-) → Beta-2 (+) → Beta-3 (-)
```

\[
\theta = \min(20,25)=20
\]

Nova solução:

| Origem/Destino | Região 1 | Região 2 | Região 3 |
|---|---:|---:|---:|
| CD Alfa | 40 | 0 | 20 |
| CD Beta | 0 | 45 | 5 |

## 3. MODI — Iteração 2

Células básicas: Alfa-1, Alfa-3, Beta-2 e Beta-3.

Definindo \(u_{\text{Alfa}}=0\):

\[
v_1=7
\]

\[
v_3=4
\]

\[
u_{\text{Beta}}=6-4=2
\]

\[
v_2=5-2=3
\]

Custos reduzidos:

| Célula | Cálculo | \(\Delta\) |
|---|---|---:|
| Alfa → Região 2 | \(9-(0+3)\) | 6 |
| Beta → Região 1 | \(8-(2+7)\) | -1 |

Beta → Região 1 entra na solução.

Ciclo:

```text
Beta-1 (+) → Alfa-1 (-) → Alfa-3 (+) → Beta-3 (-)
```

\[
\theta = \min(40,5)=5
\]

## Solução ótima

| Origem/Destino | Região 1 | Região 2 | Região 3 |
|---|---:|---:|---:|
| CD Alfa | 35 | 0 | 25 |
| CD Beta | 5 | 45 | 0 |

\[
Z = 35(7)+25(4)+5(8)+45(5)
\]

\[
\boxed{Z = R\$ 610}
\]

---

# Exercício 2 — Usinas e cidades

## Dados

| Origem/Destino | Cidade A | Cidade B | Cidade C | Oferta |
|---|---:|---:|---:|---:|
| Usina 1 | 6 | 8 | 10 | 50 |
| Usina 2 | 9 | 7 | 4 | 42 |
| Usina 3 | 5 | 6 | 8 | 28 |
| Demanda | 45 | 35 | 40 | 120 |

Oferta total = 120  
Demanda total = 120  
Problema balanceado.

## 1. Solução inicial pelo Canto Noroeste

1. Usina 1 → Cidade A: 45
2. Usina 1 → Cidade B: 5
3. Usina 2 → Cidade B: 30
4. Usina 2 → Cidade C: 12
5. Usina 3 → Cidade C: 28

| Origem/Destino | Cidade A | Cidade B | Cidade C |
|---|---:|---:|---:|
| Usina 1 | 45 | 5 | 0 |
| Usina 2 | 0 | 30 | 12 |
| Usina 3 | 0 | 0 | 28 |

Custo inicial:

\[
Z = 45(6)+5(8)+30(7)+12(4)+28(8)
\]

\[
\boxed{Z = R\$ 792}
\]

## 2. MODI

Células básicas: U1-A, U1-B, U2-B, U2-C e U3-C.

Definindo \(u_1=0\):

\[
v_A=6
\]

\[
v_B=8
\]

\[
u_2=7-8=-1
\]

\[
v_C=4-(-1)=5
\]

\[
u_3=8-5=3
\]

Custos reduzidos:

| Célula | Cálculo | \(\Delta\) |
|---|---|---:|
| Usina 1 → Cidade C | \(10-(0+5)\) | 5 |
| Usina 2 → Cidade A | \(9-(-1+6)\) | 4 |
| Usina 3 → Cidade A | \(5-(3+6)\) | -4 |
| Usina 3 → Cidade B | \(6-(3+8)\) | -5 |

A célula Usina 3 → Cidade B entra, pois possui o menor custo reduzido.

Ciclo:

```text
U3-B (+) → U2-B (-) → U2-C (+) → U3-C (-)
```

\[
\theta = \min(30,28)=28
\]

## Solução ótima

| Origem/Destino | Cidade A | Cidade B | Cidade C |
|---|---:|---:|---:|
| Usina 1 | 45 | 5 | 0 |
| Usina 2 | 0 | 2 | 40 |
| Usina 3 | 0 | 28 | 0 |

\[
Z = 45(6)+5(8)+2(7)+40(4)+28(6)
\]

\[
\boxed{Z = R\$ 652}
\]

Após recalcular os custos reduzidos, todos são positivos. Portanto, esta é a solução ótima.

---

# Exercício 3 — Fornecimento de água

## Dados e balanceamento

| Origem/Destino | Bairro A | Bairro B | Oferta |
|---|---:|---:|---:|
| Poço 1 | 3 | 5 | 20 |
| Poço 2 | 4 | 2 | 25 |
| Poço 3 | 6 | 7 | 15 |
| Demanda | 40 | 35 | 75 |

Oferta total:

\[
20+25+15=60
\]

Demanda total:

\[
40+35=75
\]

Faltam 15 mil litros. Portanto, adiciona-se uma origem fictícia com oferta 15 e custo zero.

| Origem/Destino | Bairro A | Bairro B | Oferta |
|---|---:|---:|---:|
| Poço 1 | 3 | 5 | 20 |
| Poço 2 | 4 | 2 | 25 |
| Poço 3 | 6 | 7 | 15 |
| Poço fictício | 0 | 0 | 15 |
| Demanda | 40 | 35 | 75 |

## 1. Solução inicial pelo Canto Noroeste

1. Poço 1 → Bairro A: 20
2. Poço 2 → Bairro A: 20
3. Poço 2 → Bairro B: 5
4. Poço 3 → Bairro B: 15
5. Poço fictício → Bairro B: 15

| Origem/Destino | Bairro A | Bairro B |
|---|---:|---:|
| Poço 1 | 20 | 0 |
| Poço 2 | 20 | 5 |
| Poço 3 | 0 | 15 |
| Poço fictício | 0 | 15 |

Custo inicial:

\[
Z=20(3)+20(4)+5(2)+15(7)+15(0)
\]

\[
\boxed{Z = R\$ 255}
\]

## 2. MODI — Iteração 1

Células básicas: P1-A, P2-A, P2-B, P3-B e Fictício-B.

Definindo \(u_1=0\):

\[
v_A=3
\]

\[
u_2=4-3=1
\]

\[
v_B=2-1=1
\]

\[
u_3=7-1=6
\]

\[
u_F=0-1=-1
\]

Custos reduzidos:

| Célula | Cálculo | \(\Delta\) |
|---|---|---:|
| Poço 1 → Bairro B | \(5-(0+1)\) | 4 |
| Poço 3 → Bairro A | \(6-(6+3)\) | -3 |
| Fictício → Bairro A | \(0-(-1+3)\) | -2 |

Poço 3 → Bairro A entra na solução.

Ciclo:

```text
P3-A (+) → P2-A (-) → P2-B (+) → P3-B (-)
```

\[
\theta = \min(20,15)=15
\]

Nova solução:

| Origem/Destino | Bairro A | Bairro B |
|---|---:|---:|
| Poço 1 | 20 | 0 |
| Poço 2 | 5 | 20 |
| Poço 3 | 15 | 0 |
| Poço fictício | 0 | 15 |

## 3. MODI — Iteração 2

A célula Poço fictício → Bairro A ainda apresenta custo reduzido negativo.

Ciclo:

```text
Fictício-A (+) → Fictício-B (-) → P2-B (+) → P2-A (-)
```

\[
\theta = \min(15,5)=5
\]

## Solução ótima

| Origem/Destino | Bairro A | Bairro B |
|---|---:|---:|
| Poço 1 | 20 | 0 |
| Poço 2 | 0 | 25 |
| Poço 3 | 15 | 0 |
| Poço fictício | 5 | 10 |

\[
Z = 20(3)+25(2)+15(6)+5(0)+10(0)
\]

\[
\boxed{Z = R\$ 200}
\]

A origem fictícia fornece 15 mil litros, representando a demanda que não será atendida:

- Bairro A: 5 mil litros sem atendimento.
- Bairro B: 10 mil litros sem atendimento.