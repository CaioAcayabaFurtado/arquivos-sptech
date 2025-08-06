import java.util.*

fun main() {

    //val snTexto = Scanner(System.`in`)

    //print("Digite seu nome:")
    //val nome = snTexto.nextLine()

    //print("Digite seu nome:")
    //val nome2 = snTexto.nextLine()

    //println("Bem vindo $nome e $nome2!")


    val nome = pergunta("Digite o primeiro nome: ")
    val nome2 = pergunta("Digite o segundo nome: ")
    resposta(nome, nome2)

}


fun pergunta(texto: String): String{

    val snTexto = Scanner(System.`in`)
    print(texto)
    val nome = snTexto.nextLine()
    return nome

}


fun resposta (nome: String, nome2: String){

    println("Bem vindo $nome e $nome2!")

}
