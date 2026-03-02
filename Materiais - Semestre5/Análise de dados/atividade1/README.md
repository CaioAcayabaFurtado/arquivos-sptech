# Documentação — Tratamento de Dados

## Objetivo
Ler o arquivo `detalhe_votacao.csv`, aplicar limpeza e padronização dos dados e gerar o arquivo final `detalhe_votacao_tratado.xlsx`.

## Etapas executadas

1. **Leitura do CSV**

2. **Limpeza inicial**
   - Remoção de espaços extras no início/fim dos campos.
   - Padronização dos nomes das colunas (`strip`).

3. **Tratamento de datas**
   - Identificação automática das colunas com prefixo `DT_`.
   - Conversão para formato de banco/SGBD: `YYYY-MM-DD`.
   - Valores inválidos foram convertidos para nulo.

4. **Tratamento de campos numéricos**
   - Identificação das colunas com prefixos `QT_`, `NR_`, `CD_` e `ANO_`.
   - Conversão para tipo numérico (`Int64`)

5. **Padronização de texto**
   - Remoção de acentos.
   - Conversão para letras maiúsculas.
   - Remoção de quebras de linha/tabs e caracteres de ruído (ex.: `�`).

6. **Enriquecimento**
   - Criação da coluna `PERC_COMPARECIMENTO` com a fórmula:  
     `QT_COMPARECIMENTO / QT_APTOS * 100`.

7. **Qualidade final e saída**
   - Remoção de registros duplicados (`drop_duplicates`).
   - Exportação para Excel em `detalhe_votacao_tratado.xlsx`.

## Regras da atividade atendidas

- Dados texto sem acentuação.  
- Dados texto padronizados em maiúsculo.  
- Dados em formato compatível com SGBD (principalmente datas).  
- Leitura de CSV e geração de arquivo XLS com dados tratados.