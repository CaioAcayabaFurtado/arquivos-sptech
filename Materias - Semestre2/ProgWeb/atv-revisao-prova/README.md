# ⚡ Desafio Kotlin: Super-Heróis e Seus Gadgets Tecnológicos

## 🌟 Contexto

Parabéns, desenvolvedor(a), você acaba de ser recrutado para construir o sistema de gerenciamento de super-heróis! Sua missão é criar classes que representem heróis com poderes especiais e seus gadgets tecnológicos de última geração. Mas cuidado! Cada herói tem características únicas, e nem todos podem usar gadgets.

Neste desafio, vamos explorar o uso de **herança**, **abstração** e **interfaces** para tornar o sistema flexível e dinâmico.

---

## 📜 Requisitos

### Todos os atributos que forem (private set) devem ter métodos com validações.

### 1. A Classe Abstrata `Heroi`

Todos os super-heróis devem herdar da classe `Heroi`, que define as características fundamentais.

**Atributos**:
- `nome: String - private set`: o nome do herói.
- `poderPrincipal: String - private set`: uma descrição do principal poder do herói (private set).
- `nivelEnergia: Int - private set`: representa a energia disponível do herói, que deve ser um número positivo (private set).

**Métodos Abstratos**:
1. `usarPoder(): String` - Quem implementar, retorna uma mensagem descrevendo como o herói usa seu poder principal.
2. `descrever(): String` - Quem implementar, retorna detalhes sobre o herói, incluindo nome, poder principal e energia.

**Método Concreto**:
- `recuperarEnergia(): String` - Aumenta o nivelEnergia em +20 (valor fixo) e retorna exatamente essa frase (substituindo os valores):  
  - `"O herói [nome] está recuperando energia e agora tem [nivelEnergia] pontos."`.

**Validações (nos métodos set)**:
- O `nome` deve ter no mínimo 3 caracteres.
- O `poderPrincipal` deve ter no mínimo 10 caracteres.
- O `nivelEnergia` deve ser um número positivo (maior que 0).

---

### 2. A Interface `UsuarioGadgets`

Os heróis tecnológicos precisam de gadgets para melhorar suas habilidades. Crie a interface `UsuarioGadgets`.

**Métodos da Interface (abstratos)**:
1. `equiparGadget(gadget: String): String` - Quem implementar, deve retorna uma mensagem informando que o gadget foi equipado.
2. `usarGadget(): String` - Quem implementar, deve retornar uma descrição do que o gadget equipado faz.

---

### 3. A Classe concerta `HeroiTecnologico` (herda de `Heroi` e implementa `UsuarioGadgets`)

Essa classe representa heróis como o famoso "Cavaleiro de Gotham", que combinam habilidades humanas com gadgets de alta tecnologia.

**Atributos Adicionais**:
- `gadgetAtual: String - private set`: o gadget atualmente equipado (nulo se nenhum gadget estiver equipado) - só pode ser alterado via método "equiparGadget".

**Implementações dos Métodos Abstratos**:
1. `usarPoder()` - retorna exatamente essa frase (substituindo os valores):  
   - `"O herói tecnológico [nome] usa seu poder: [poderPrincipal], auxiliado por tecnologia avançada!"`.
2. `descrever()` - retorna exatamente essa frase (substituindo os valores):  
   - `"Herói [nome]: Poder Principal - [poderPrincipal], Energia - [nivelEnergia]."`.

**Implementações da Interface `UsuarioGadgets`**:
1. `equiparGadget(gadget: String): String` - Atualiza `gadgetAtual` e retorna uma frase como essa (substituindo os valores):  
   - `"Gadget [gadgetAtual] equipado pelo herói [nome]."`. 
   
2. `usarGadget()` - retorna exatamente essa frase (substituindo os valores):  
   - Faça uma condicional para retornar `"O gadget [gadgetAtual] foi ativado!"` caso tenha gadgetAtual, ou `"Nenhum gadget equipado"` se o gadgetAtual for vazio ("").

---

### 4. A Classe `HeroiSobrenatural` (apenas herda de `Heroi`)

Nem todos os heróis precisam de gadgets! A classe `HeróiSobrenatural` representa aqueles que possuem poderes inatos, como o "Guardião do Raio".

**Atributos Adicionais**:
- `poderSecundario (String) - private set`: uma descrição de um segundo poder especial do herói.

**Implementações dos Métodos Abstratos**:
1. `usarPoder()` - retorna exatamente essa frase (substituindo os valores):  
   - `"O herói sobrenatural [nome] libera seu poder principal: [poderPrincipal]!"`.
2. `descrever()` - retorna exatamente essa frase (substituindo os valores):  
   - `"Herói [nome]: Poder Principal - [poderPrincipal], Poder Secundário - [poderSecundario], Energia - [nivelEnergia]."`.

**Validações (no método set)**:
- O `poderSecundario` deve ter no mínimo 10 caracteres.

---

## 🎉 Boa sorte, herói(a)! Mostre que você é digno(a) de salvar o mundo com Kotlin!  
