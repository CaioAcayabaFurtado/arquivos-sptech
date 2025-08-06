abstract class Heroi {

    var nome: String = ""
        private set
    var poderPrincipal: String = ""
        private set
    var nivelEnergia: Int = 0
        private set

    abstract fun usarPoder(): String

    abstract fun descrever(): String

    fun recuperarEnergia(): String {
        nivelEnergia += 20
        return "O heroi $nome está recuperando energia e agora tem $nivelEnergia pontos."
    }

    fun setNome(novoNome: String) {
        if (novoNome.length >= 3) {
            this.nome = novoNome
        }
    }

    fun setPoderPrincipal(novoPoderPrincipal: String) {
        if (novoPoderPrincipal.length >= 10) {
            this.poderPrincipal = novoPoderPrincipal
        }
    }

    fun setNivelEnergia(novoNivelEnergia: Int) {
        if (novoNivelEnergia > 0) {
            this.nivelEnergia = novoNivelEnergia
        }
    }
}