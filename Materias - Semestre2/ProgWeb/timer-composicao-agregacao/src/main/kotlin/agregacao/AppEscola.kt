package agregacao

fun main() {
    val professor = Professor()
    professor.lattes="123456"
    val escola = Escola()
    escola.nome="SPTECH"

    escola.adicionarProfessor(professor)
    escola.listarProfessores().forEach(){
        println(professor.lattes)
    }

}