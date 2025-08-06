class Habilidade {

    var nome: String = ""
        private set

    var poder: Int = 0
        private set

    fun setNome(nome: String) {
        if (nome.length >= 2) {
            this.nome = nome
        }
    }

    fun setPoder(poder: Int) {
        if (poder >= 0) {
            this.poder = poder
        }
    }
}
