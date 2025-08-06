package pt2

fun main() {
    val pessoa = Pessoa()
    pessoa.nome = "John Doe"

    println(pessoa.nome)
    println(pessoa.andar())
    println("------------Aluno------------")
    val aluno = Aluno()
    aluno.nome="É o tal do Joãozin PJL"
    println(aluno.nome)
    println(aluno.andar())
    aluno.ra="123456"
    println(aluno.estudar())
    println("------------Professor--------")
    val professor = Professor()
    professor.nome="Albert Einstein"
    println(professor.nome)
    println(professor.andar())
    professor.lattes="123456"
    println(professor.prepararProva())
}