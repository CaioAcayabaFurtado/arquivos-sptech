import java.util.Scanner

fun main() {

    val snTexto = Scanner(System.`in`)
    print("Digite o seu nome: ")
    val nome = snTexto.nextLine()
    print("Digite o nome do seu bairro: ")
    val bairro = snTexto.nextLine()
    println("Você se chama $nome e mora no bairro $bairro")
}