# Atividade de Cadastro de Produtos em Kotlin

## Objetivo:
Desenvolver um sistema de gerenciamento de produtos com validação de dados e controle de estoque.

## 1. Criação da Classe Produto:

Utilize a classe `Produto` já criada e, dentro dela, defina os seguintes atributos **(NÃO CRIE "CONSTRUTOR"... CRIE COMO ATRIBUTOS DE CLASSE/INSTÂNCIA):**

> **OBS:** Todos os atributos devem ter o `set` privado (exceto o `id`).

- **id** (tipo: `Int`, valor padrão: `0`): Representa o identificador único do produto (**PÚBLICO**).
- **nome** (tipo: `String`, valor padrão: `""`): O nome do produto.
- **categoria** (tipo: `String`, valor padrão: `""`): A categoria do produto.
- **preco** (tipo: `Double`, valor padrão: `0.0`): O preço do produto.
- **fabricante** (tipo: `String`, valor padrão: `""`): O fabricante do produto.

> **OBS:** Todos os atributos devem ter o `set` privado (exceto o `id`).

## 2. Métodos da Classe Produto:

Implemente métodos **encapsulados** com validações para garantir a consistência dos dados do produto.

### a) Método para definir o nome do produto:
- **Método**: `setNome(novoValor: String)` - sem retorno.
- **Objetivo**: Definir o nome do produto.
- **Validação**: O nome do produto deve ter no mínimo 3 caracteres. Caso contrário, exiba a mensagem: `"Nome inválido! Deve ter mais de 3 caracteres."`

### b) Método para definir a categoria do produto:
- **Método**: `setCategoria(novoValor: String)` - sem retorno.
- **Objetivo**: Definir a categoria do produto.
- **Validação**: A categoria do produto deve ter no mínimo 5 caracteres. Caso contrário, exiba a mensagem: `"Categoria inválida! Deve ter mais de 5 caracteres."`

### c) Método para definir o preço do produto:
- **Método**: `setPreco(novoValor: Double)` - sem retorno.
- **Objetivo**: Definir o preço do produto.
- **Validação**: O valor do produto deve ser maior que 0. Caso contrário, exiba a mensagem: `"Preço inválido! Deve ser maior que 0."`

### d) Método para definir o fabricante do produto:
- **Método**: `setFabricante(novoValor: String)` - sem retorno.
- **Objetivo**: Definir o fabricante do produto.
- **Validação**: O fabricante do produto deve ter no mínimo 2 caracteres. Caso contrário, exiba a mensagem: `"Fabricante inválido! Deve ter mais de 2 caracteres."`

## 3. Métodos da Classe ProdutoRepositorio:

Utilize a classe `ProdutoRepositorio` já criada e, além do método `configurar` existente, implemente os seguintes métodos:

### a) Inserir novo produto:
- **Método**: `inserir(novoProduto: Produto): Boolean`
- **Retorno**: Verifica a quantidade de linhas afetadas ao inserir. Se for maior que 0, retorna `true`, senão, `false`.

### b) Listar todos os produtos:
- **Método**: `listar(): List<Produto>`
- **Retorno**: Lista com todos os produtos (não é necessário validação).

### c) Verificar se um produto existe por id:
- **Método**: `existePorId(id: Int): Boolean`
- **Retorno**: Verifica a quantidade de produtos "existentes" ao contar. Se for maior que 0, retorna `true`, senão, `false`.

### d) Buscar um produto por id:
- **Método**: `buscarPorId(id: Int): Produto`
- **Retorno**: Retorna o produto.

### e) Atualizar um produto por id:
- **Método**: `atualizarPorId(id: Int, produtoParaAtualizar: Produto): Boolean`
- **Retorno**: Verifica a quantidade de linhas afetadas ao atualizar. Se for maior que 0, retorna `true`, senão, `false`.

### f) Deletar um produto por id:
- **Método**: `deletarPorId(id: Int): Boolean`
- **Retorno**: Verifica a quantidade de linhas afetadas ao deletar. Se for maior que 0, retorna `true`, senão, `false`.

## Considerações Finais:
- Não é necessário desenvolver nada na função `main`. Apenas as classes devem ser implementadas.
- Existem testes unitários disponíveis para facilitar a correção do que foi desenvolvido.
- Caso os testes unitários sejam um impeditivo, utilize a função `main` para testar o que foi feito. Os testes cobrem cenários possíveis, mas nada impede de você criar cenários adicionais na função `main`.
