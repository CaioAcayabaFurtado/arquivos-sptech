import java.util.Scanner

fun main(){

    val snString = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)

    print("Digite seu nome: ")
    val nome = snString.nextLine()

    print("Digite sua idade: ")
    val idade = snNumero.nextInt()

    println("Seu nome é $nome, você tem $idade anos de idade.")



    print("Digite seu email: ")
    val email = snString.nextLine()

    print("Digite sua renda: ")
    val renda = snNumero.nextDouble()

    println("Seu email é $email, você tem R$$renda de renda.")
}