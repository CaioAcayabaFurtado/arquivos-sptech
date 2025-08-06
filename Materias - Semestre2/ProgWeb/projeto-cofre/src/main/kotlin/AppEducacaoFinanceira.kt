import java.util.Scanner

fun main() {
    val snTexto = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)

    val cofre = Cofrinho()

    println("Bem vindo ao app de educação financeira!")
    println("Para começar, digite o seu objetivo:")
    cofre.objetivo = snTexto.nextLine()
    println("Digite o valor da sua meta: (Exemplo: 2000,00)")
    cofre.meta = snTexto.nextDouble()

    while (true) {

        Thread.sleep(1000)
        println("")
        println("---Cofrinho---")
        println("1 - Depositar no cofre")
        println("2 - Retirar do cofre")
        println("3 - Detalhes")
        println("4 - Sair")
        println("Digite o número de uma das ações:")
        var acao = snNumero.nextInt()

        when (acao) {
            1 -> {
                println("Digite o valor do depósito:")
                cofre.valorDeposito = snNumero.nextDouble()
                cofre.depositar()
            }

            2 -> {
                println("Digite o valor da retirada:")
                cofre.valorRetirada = snNumero.nextDouble()
                cofre.retirar()
            }

            3 -> cofre.descrever()
            4 -> break
            else -> println("Opção inválida")
        }
    }




}