# Atividade de Cadastro de Filmes em Kotlin

**BAIXE O ZIP NO FINAL DO ENUNCIADO PARA A REALIZAÇÃO DA ATIVIDADE.**

## Objetivo

Desenvolver um sistema de gerenciamento de filmes com validação de dados e controle de disponibilidade para locação.

## 1. Criação da Classe Filme

Utilize a classe `Filme` já criada e, dentro dela, implemente os seguintes atributos (NÃO CRIE CONSTRUTOR, CRIE COMO ATRIBUTOS DE CLASSE/INSTÂNCIA):

**OBS:** TODOS OS ATRIBUTOS DEVEM TER O "SET" PRIVADO (MENOS `id`).

- `id` (tipo: INTEIRO, valor padrão: 0): Representa o identificador único do filme (PÚBLICO).
- `nome` (tipo: TEXTO, valor padrão ""): O nome do filme.
- `genero` (tipo: TEXTO, valor padrão ""): O gênero do filme.
- `anoLancamento` (tipo: INTEIRO, valor padrão: 0): O ano de lançamento do filme.
- `duracao` (tipo: INTEIRO, valor padrão: 0): A duração do filme em minutos.
- `alugado` (tipo: BOOLEANO, valor padrão: false): Indica se o filme está alugado.

## 2. Métodos da Classe Filme

Implemente métodos "encapsulados" com validações para garantir a consistência dos dados do filme.

### a) Método para definir o nome do filme

- **Método**: `setNome(novoValor: String)`
- **Objetivo**: Definir o nome do filme.
- **Validação**: O nome do filme deve ter 2 ou mais caracteres, caso contrário, não faça nada.

### b) Método para definir o gênero do filme

- **Método**: `setGenero(novoValor: String)`
- **Objetivo**: Definir o gênero do filme.
- **Validação**: O gênero do filme deve ter 2 ou mais caracteres, caso contrário, não faça nada.

### c) Método para definir o ano de lançamento do filme

- **Método**: `setAnoLancamento(novoValor: Int)`
- **Objetivo**: Definir o ano de lançamento do filme.
- **Validação**: O ano de lançamento deve ser 1895 ou superior (o primeiro filme foi em 1895), caso contrário, não faça nada.

### d) Método para definir a duração do filme

- **Método**: `setDuracao(novoValor: Int)`
- **Objetivo**: Definir a duração do filme.
- **Validação**: A duração do filme deve ser 15 minutos ou mais, caso contrário, não faça nada.

### e) Método para converter a duração do filme em horas e minutos

- **Método**: `converterDuracaoEmHoras(): String`
- **Objetivo**: Converter a duração do filme para o formato "hh:mm".
- **Retorno**: Retorna a duração do filme no formato "hh:mm".

**Explicação**: Esse método deve calcular a duração do filme em horas e minutos, a partir da quantidade total de minutos, e formatar o resultado para aparecer no formato "hh:mm", com zeros à esquerda.

**Como fazer**:
- Divida a quantidade total de minutos por 60 para obter as horas. `horas = duracao / 60`;
- O resto da divisão por 60 para obter os minutos. `minutos = duracao % 60`;
- Depois, formate o resultado que vai retornar no método para ter exatamente 2 dígitos em cada parte.

**Por exemplo**:

- Para um filme com 2 horas e 30 minutos de duração (150 minutos), o método deve retornar "02:30".
- Para um filme com 1 hora e 5 minutos de duração (65 minutos), o retorno deve ser "01:05".

Para garantir que o formato tenha sempre dois dígitos, use o método `padStart` da classe `String`. O `padStart(2, '0')` preenche o número com zeros à esquerda até que tenha exatamente 2 dígitos.


### f) Método para descrever o filme

- **Método**: `descrever(): String`

- **Objetivo**: Descrever o filme com todas as informações.
- **Retorno**: Retorna uma string com todas as informações do filme.
  - Exemplo:
  - ```kotlin
        return """
            Id: ${id}
            Nome do Filme: ${nome}
            Gênero do Filme: ${genero}
            Ano de Lançamento: ${anoLancamento}
            Duração: ${converterDuracaoEmHoras()}
            Alugado: ${if (alugado) "Sim" else "Não"}
        """.trimIndent()
    ```

## 3. Métodos da Classe FilmeRepositorio

Utilize a classe `FilmeRepositorio` já criada e, dentro dela, além do método `configurar` que já existe, crie os seguintes métodos:

### a) Criar a tabela de filmes

- **Método:** `criarTabela()`
- **Retorno:** Não retorna nada.
- **Observação:** O método deve criar a tabela de filmes no banco de dados de acordo com os atributos. Caso seja criada erroneamente, o teste falhará.

### b) Inserir novo filme

- **Método:** `inserir(novoFilme: Filme): Boolean`
- **Retorno:** Verifica a quantidade de linhas afetadas ao inserir. Se for maior que 0, retorna `true`; caso contrário, retorna `false`.

### c) Listar todos os filmes

- **Método:** `listar(): List<Filme>`
- **Retorno:** Retorna uma lista com todos os filmes cadastrados.

### d) Verificar se um filme existe por id

- **Método:** `existePorId(id: Int): Boolean`
- **Retorno:** Verifica a quantidade de filmes existentes ao "contar". Se for maior que 0, retorna `true`; caso contrário, retorna `false`.

### e) Buscar um filme por id

- **Método:** `buscarPorId(id: Int): Filme`
- **Retorno:** Retorna o filme correspondente ao `id`.

### f) Atualizar um filme por id

- **Método:** `atualizarPorId(id: Int, filmeParaAtualizar: Filme): Boolean`
- **Retorno:** Verifica a quantidade de linhas afetadas ao atualizar. Se for maior que 0, retorna `true`; caso contrário, retorna `false`.

### g) Deletar um filme por id

- **Método:** `deletarPorId(id: Int): Boolean`
- **Retorno:** Verifica a quantidade de linhas afetadas ao deletar. Se for maior que 0, retorna `true`; caso contrário, retorna `false`.

### h) Verificar se um filme está alugado ou não por id

- **Método:** `alugadoPorId(id: Int): Boolean`
- **Retorno:** Verifica se o filme está alugado ou não. Se estiver alugado, retorna `true`; caso contrário, retorna `false`.
- **Dica:** Faça um `SELECT` que retorne o campo `alugado` e NÃO `*` na busca por id. O resultado da queryForObject deve ser um `Boolean`.
  - Exemplo: Boolean::class.java,

### I) Alugar um filme por id

- **Método:** `alugarPorId(id: Int): Boolean`
- **Retorno:** Atualiza o campo `alugado` para `true`  do filme por id e verifica a quantidade de linhas afetadas. Se for maior que 0, retorna `true`; caso contrário, retorna `false`.

## Considerações Finais

- **NÃO** será necessário desenvolver nada na `main`, apenas as classes.
- Existem testes unitários para facilitar a correção do que for desenvolvido.
- Caso os testes unitários sejam um impeditivo, utilize a `main` para testar o que acabou de fazer (os testes já verificam cenários possíveis, mas nada impede que você crie cenários na `main` também).
- **NÃO ALTERE** o nome dos métodos e atributos propostos nos requisitos.

