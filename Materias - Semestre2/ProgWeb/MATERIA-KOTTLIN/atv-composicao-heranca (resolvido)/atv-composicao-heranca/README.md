# 🛠️ Desafio Kotlin: Forjando um Personagem com Habilidades Épicas!

## ⚔️ Contexto

Bem-vindo(a), nobre desenvolvedor(a)! Hoje você embarca em uma jornada de criação no mundo dos códigos, onde vai forjar um **Personagem** poderoso com habilidades únicas. Mas não é só isso! Este personagem herdará atributos da lendária classe `Pessoa`! Prepare-se para definir características épicas, moldar poderes e garantir que ele esteja pronto para qualquer aventura digital.

---

## 📜 Requisitos

### 1. A Lendária Classe `Pessoa` 🧙‍♂️

A primeira tarefa é criar uma classe base chamada `Pessoa` que trará as seguintes características para o nosso personagem:

- `nome (String)`: um nome poderoso que representa a identidade da pessoa (private set).

**Obs**: o atributo `nome` deve ter um método de configuração `setNome(valor: String)` com a seguinte proteção mística:

**Proteção Mística (Validação)**:
- O `nome` precisa ter pelo menos 2 caracteres (afinal, heróis e heroínas merecem nomes dignos!).

**Poderes Especiais (Métodos)**:
1. `apresentar()`: retorna a frase encantada **"Olá, meu nome é [nome da pessoa]."** Atenção: a mensagem deve ser EXATAMENTE esta! Respeite o feitiço ou ele falhará.

---

### 2. A Força das Habilidades `Habilidade` 💥

Agora vamos criar a classe `Habilidade`, para dar poderes ao nosso personagem. Cada habilidade deve ser bem definida, com atributos únicos e misticamente protegidos:

- `nome (String)`: o nome da habilidade (private set) – para diferenciar "Rajada Explosiva" de "Sopro Congelante".
- `poder (Int)`: o nível de poder da habilidade, expresso por um número inteiro positivo (private set).

**Obs**: cada atributo precisa de um método de configuração (`setNome(valor: String)` e `setPoder(valor: Int)`) com as seguintes proteções místicas:

**Proteções Místicas (Validações)**:
- O `nome` da habilidade deve ter pelo menos 2 caracteres (nomes grandiosos, por favor!).
- O `poder` deve ser um número positivo (um poder fraco não é poder de verdade!).

---

### 3. A Classe do Destino: `Personagem` 🦸‍♂️

Por fim, chegamos à classe mais esperada: `Personagem`! Esta classe, herdada de `Pessoa`, será equipada com habilidades especiais, formando a essência do nosso herói ou heroína.

Além dos atributos herdados, o `Personagem` deve ter uma lista privada de habilidades, para armazenar os poderes lendários adquiridos:

- `habilidades`: uma lista privada (`private val`) de `Habilidade` associadas ao personagem.

**Habilidades Especiais do Personagem (Métodos)**:
1. `adicionarHabilidade(habilidade: Habilidade)`: Adiciona uma habilidade à lista, aumentando os poderes do personagem.
2. `listarHabilidades()`: Retorna uma lista das habilidades acumuladas pelo personagem, para que todos possam ver o seu verdadeiro poder.
3. `removerHabilidade(nomeHabilidade: String)`: Remove uma habilidade específica da lista, caso o personagem decida abrir mão de um poder (use o método de lista adequado para encontrar a habilidade pelo nome).

---

## 🌟 Boa sorte, e que seu personagem se torne lendário!
