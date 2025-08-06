fun main() {

    //declatando uma lista em branco
    val produtos = mutableListOf<String>()

    while (true) {
        println("Bem vindo!!!")
        println("1 - Adicionar produtos")
        println("2 - Remover produtos")
        println("3 - Atualizar produtos")
        println("4 - Todos os produtos")
        println("5 - Sair")
        println("Sair")

        val opcaoEscolhida = readln().toInt()
        when (opcaoEscolhida) {
            1 -> {
                println("Digite o nome do produto:")
                val novoProduto = readln()
                produtos.add(novoProduto)
            }

            2 -> {
                println("Digite a posição do produto para ser removido:")
                val posicao = readln().toInt()
                produtos.removeAt(posicao)
            }

            3 -> {
                println("Em manutenção")
            }

            4 -> {
                println("Produtos: $produtos")
            }

            5 -> break
            else -> println("Opção invalida")

        }
        Thread.sleep(5000)
    }
}