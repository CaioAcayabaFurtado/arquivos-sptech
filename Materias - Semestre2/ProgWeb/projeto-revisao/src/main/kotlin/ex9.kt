import java.util.Scanner

fun main() {
    val snTexto = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)

    println("Digite uma frase:")
    val frase = snTexto.nextLine()
    println("Digite quantas vezes quer repetir a frase:")
    val numero = snNumero.nextInt()

    for (i in 1 .. numero){
        println("$frase")
    }
}