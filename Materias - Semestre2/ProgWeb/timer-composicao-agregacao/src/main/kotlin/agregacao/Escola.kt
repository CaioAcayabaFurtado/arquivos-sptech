package agregacao

class Escola {
    var nome:String=""

    private val professores = mutableListOf<Professor>()

    fun adicionarProfessor(novoProfessor: Professor){
        professores.add(novoProfessor)
    }
    fun listarProfessores():List<Professor>{
        return professores
    }
}

