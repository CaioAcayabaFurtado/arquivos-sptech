fun main() {

    val pessoa1 = Pessoa("João", 18)
    val pessoa2 = Pessoa("Maria", 18)
    println("Pessoa 1 - nome: ${pessoa1.nome}")
    println("Pessoa 1 - nome: ${pessoa1.idade}")

    val pessoas = mutableListOf<Pessoa>()
    pessoas.add(pessoa1)
    pessoas.add(pessoa2)

    for (pessoaDaVez in pessoas){
        println("A pessoa tem o nome ${pessoaDaVez.nome}, com a idade ${pessoaDaVez.idade}")
    }

    pessoa1.respirar()
    pessoa2.respirar()
    pessoa2.ola()

    pessoa1.nome = "José"
    pessoa1.ola()
}