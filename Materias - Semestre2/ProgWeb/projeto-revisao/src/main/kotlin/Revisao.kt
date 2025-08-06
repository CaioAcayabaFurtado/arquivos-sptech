import java.util.Scanner
import kotlin.math.pow

fun main() {

    val soma = 10 + 10
    val subt = 10 - 5
    val divs = 10 / 2
    val mult = 10 * 2

    println("Soma ${10 + 10}")
    println("Soma $soma")
    println("Subtração $subt")
    println("Divisão $divs")
    println("Multiplicação $mult")

    // val potencia = 5.0 * 5.0 // tipo inferido
    val potencia: Double = 5.0.pow(2)// tipo declarativo
    // val potencia: 5.0.pow(2)// funciona sem declarar
    println("Potencia $potencia")
    println("Potencia ${"%.2f".format(potencia)}") // formatando as casas decimais

    val snTexto = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)

    print("Digite seu nome:")
    val nome = snTexto.nextLine()
    println("Bem vindo, $nome!")

    print("Digite um numero:")
    val numero = snTexto.nextInt()

    when (numero){
        1 -> println("É 1")
        2 -> println("É 2")
        3 -> println("É 3")
        in 4..6 -> println("É entre 4 e 6")
        7,8,9 -> println("É 7, 8 ou 9")
        else -> println("Invalido")
    }

    //when {
    //    numero == 1 -> println("É 1")
    //    numero > 1 -> println("É maior que 1")
    //}

}