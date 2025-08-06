import java.util.Scanner

fun main() {
    val snNumero = Scanner(System.`in`)

    print("Digite a sua altura:")
    val numero = snNumero.nextDouble()

    when{
        numero >= 1.8 -> println("Pode jogar basquete")
        numero < 1.8 -> println("Não pode jogar basquete")
    }

}