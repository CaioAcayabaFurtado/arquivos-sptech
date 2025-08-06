
// FILE - AppExemploNull

fun main() {

    var nome: String? = null

    // exemplo 1
    val resposta1: String = nome ?: "Nome não definido"
    println(resposta1)

    // exemplo 2
    var resposta2 = ""
    if (nome != null) {
        resposta2 = nome
    } else {
        resposta2 = "Nome não definido"
    }
    println(resposta2)


    print("Digite seu email: ")
    // tenta ler, se for espaço em branco, coloca null e troca pra frase
    var email: String = readlnOrNull() ?: "não informado"
    println("email: $email")
    println(resposta1)

    print("Digite um número: ")
    // se for algo, converte para inteiro, se não conseguir, vira 0
    var numero: Int = readln().toIntOrNull() ?: 0
    println("Número $numero")

    // musica é do tipo "Musica" mas no momento é "nada"
    val musica: Musica? = null

    if (musica != null) {
        println("Nome: ${musica.nome}")
        println("Produtor: ${musica.produtor ?: "Sem produtor"}")
    } else {
        println("Música não existe!")
    }
}