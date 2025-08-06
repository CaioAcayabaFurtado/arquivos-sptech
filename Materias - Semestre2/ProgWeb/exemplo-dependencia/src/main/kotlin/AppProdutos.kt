fun main() {
    val produto=Produto()

    println("Digite o nome do produto:")
    produto.nome = readln()

    println("Digite o preco do produto:")
    produto.preco = readln().toDouble()

    while (true) {
        println("É brinde? 1 - É brinde / 2 - Não é brinde")
        val escolha = readln().toInt()
        when (escolha) {
            1 -> {
                produto.brinde = true
                break
            }
            2 -> break
            else -> println("Valor inválido")
        }
    }

    println("O produto ${produto.nome} custa ${produto.preco}")
}