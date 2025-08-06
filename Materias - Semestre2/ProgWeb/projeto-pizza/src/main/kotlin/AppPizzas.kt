import java.util.Scanner

fun main() {
    val snTexto = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)
    val pizza = PedidoPizza()

    println("Digite o sabor:")
        pizza.sabor = snTexto.nextLine()
    println("Digite o valor:")
        pizza.preco = snNumero.nextDouble()
    println("Digite a quantidade de amigos que vão comer:")
        pizza.quantidadeAmigos = snNumero.nextInt()
    println("Digite um cupom:")
        pizza.cupom = snTexto.nextLine()
        pizza.validarCupom()

    fun perguntar(){
        println("Deseja adicionar mais um cupom?(s/n)")
        var resp = snTexto.nextLine()
        if (resp == "s") {
            println("Digite um cupom:")
            pizza.cupom = snTexto.nextLine()
            pizza.validarCupom()
            perguntar()
        }
    }
    perguntar()

    println("-----------------------------------------------")
    pizza.descrever()

    pizza.obterValorPorAmigo()
    println("Cada amigo deve pagar R$${pizza.precoAmigo}")

    println("Cupons usados:")
    println(pizza.obterCuponsUsados())
    println("\n")
    println("Pedido finalizado!")
}