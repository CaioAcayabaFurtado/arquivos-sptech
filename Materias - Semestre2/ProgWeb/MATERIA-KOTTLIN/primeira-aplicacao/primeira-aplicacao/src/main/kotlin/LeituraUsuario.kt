import java.util.Scanner

fun main() {

    // scanner para receber textos
    val snString = Scanner(System.`in`)

    // scanner para receber números e não ter um "bug" ao receber valores
    val snNumero = Scanner(System.`in`)

    print("Digite seu nome: ") // mensagem
    val nome = snString.nextLine() // leitura do input do usuário
    println("Seu nome é $nome") // exibir resultado

    print("Digite a sua idade: ")
    val idade = snNumero.nextInt()// leitura do input do usuário para números
    println("Sua idade é $idade")

    print("Digite seu e-mail: ")
    val email = snString.nextLine()
    println("Seu e-mail é $email")

    print("Digite sua renda: ")
    val renda = snNumero.nextDouble()
    println("Sua renda é $renda")
}