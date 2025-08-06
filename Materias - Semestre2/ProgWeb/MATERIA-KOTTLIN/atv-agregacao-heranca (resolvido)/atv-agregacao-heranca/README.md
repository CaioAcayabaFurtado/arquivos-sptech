# 🏕️ Desafio Kotlin: Aventureiros e suas Missões Desafiadoras!

## 🌌 Contexto

Saudações, intrépido(a) desenvolvedor(a)! Hoje você embarcará em uma jornada para criar um sistema que gerencia **Exploradores** — personagens valentes que enfrentam desafios épicos ao se inscreverem em missões incríveis. Cada explorador faz parte de uma linhagem de **Aventureiros** com habilidades especiais e uma verdadeira sede por aventuras.

Neste desafio, exploraremos o conceito de **agregação** — ou seja, nossos exploradores podem se inscrever em missões que existem independentemente deles! Em outras palavras, uma missão pode existir sem que um explorador esteja associado a ela, e, mesmo se um explorador for removido do sistema, as missões nas quais ele estava inscrito permanecem ativas. Está pronto para expandir seu universo de Kotlin?

---

## 📜 Requisitos

### 1. A Classe Base `Aventureiro` 🌄

Todo explorador é, antes de mais nada, um aventureiro nato! Crie uma classe base chamada `Aventureiro`, contendo as características fundamentais de qualquer personagem que parte para o desconhecido:

- `nome (String)`: o nome do aventureiro (private set).
- `energia (Int)`: o nível de energia do aventureiro, que deve ser um número positivo e representa a vitalidade do personagem (private set).

**Métodos da Classe `Aventureiro`**:
1. `descansar()`: aumenta a energia do aventureiro em uma quantidade fixa de +10. Isso é fundamental para o aventureiro recuperar forças.
2. `status()`: retorna uma string com uma mensagem informando o nome do aventureiro e seu nível atual de energia. Exemplo: `"Aventureiro [nome] tem [energia] de energia."` (Exatamente esse texto, apenas substitua os valores [] por atributos).

**Validações**:
- O `nome` deve ter pelo menos 3 caracteres.
- `energia` deve ser um número positivo (maior que 0).

---

### 2. A Classe `Missao` 🎒

A segunda tarefa é criar a classe `Missao`, que representará os desafios e aventuras que os exploradores podem enfrentar.

- `nome (String)`: o nome da missão (private set).
- `descricao (String)`: uma breve descrição da missão (private set).
- `nivelDificuldade (Int)`: o nível de dificuldade da missão, que deve ser um número positivo (private set).

**Validações**:
- O `nome` precisa ter pelo menos 3 caracteres.
- A `descricao` deve ter no mínimo 10 caracteres.
- `nivelDificuldade` deve ser um número positivo (maior que 0).

**Explicação sobre Agregação**:
A classe `Missao` representa uma **agregação** em relação à classe `Explorador`. Isso significa que as missões existem independentemente dos exploradores. Uma missão pode ser criada no sistema e existir por si só, sem estar associada a um explorador. Mesmo se um explorador for excluído, as missões que ele havia se inscrito continuam existindo no sistema, pois elas não dependem da existência dos exploradores. Esta relação é importante para entender que a vida útil das missões é independente dos personagens que nelas se inscrevem.

---

### 3. A Classe `Explorador` 🧭 (Herdada de `Aventureiro`)

A classe `Explorador` será nossa classe principal, herdando as características de `Aventureiro`. Um explorador possui habilidades especiais e uma lista de missões nas quais ele está inscrito.

A classe `Explorador` deve ter os seguintes atributos adicionais:

- `nivelExperiencia (Int)`: o nível de experiência do explorador, que deve ser positivo (private set).
- `missoes`: uma lista privada de missões que o explorador escolheu enfrentar (private val).

**Métodos da Classe `Explorador`**:
1. `inscreverEmMissao(missao: Missao)`: Adiciona uma missão à lista de missões do explorador.
2. `listarMissoes(): List<Missao>`: Retorna uma lista de todas as missões nas quais o explorador está inscrito.
3. `abandonarMissao(nomeMissao: String)`: Remove uma missão específica da lista, caso o explorador decida abandoná-la.
4. `ganharExperiencia(pontos: Int)`: Aumenta o `nivelExperiencia` do explorador, permitindo que ele melhore suas habilidades ao completar missões.

**Validações**:
- Não crie um `setNivelExperiencia()`! O nível de experiência só pode ser alterado por meio do método `ganharExperiencia()`.

---

## 🌟 Desafio Extra

Na classe explorador, crie um método `dificuldadeMediaMissoes(): Int` que retorna a média de dificuldade das missões inscritas, ou zero caso não haja missões (verifique primeiramente se a lista de missões está vazia, retorne 0). Faça um filtro para calcular a média das missões (media = somaNiveis / qtdMissoes).

---

## 🎉 Boa sorte, aventureiro(a)! Que suas missões sejam desafiadoras e cheias de recompensas!s