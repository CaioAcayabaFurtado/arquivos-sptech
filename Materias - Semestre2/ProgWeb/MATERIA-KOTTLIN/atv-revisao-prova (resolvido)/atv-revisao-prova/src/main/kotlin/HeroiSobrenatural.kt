class HeroiSobrenatural : Heroi() {

    var poderSecundario: String = ""
        private set

    override fun usarPoder(): String {
        return "O herói sobrenatural $nome libera seu poder principal: $poderPrincipal!"
    }

    override fun descrever(): String {
        return "Herói $nome: Poder Principal - $poderPrincipal, Poder Secundário - $poderSecundario, Energia - $nivelEnergia."
    }

    fun setPoderSecundario(novoPoderSecundario: String) {
        if (novoPoderSecundario.length >= 10) {
            this.poderSecundario = novoPoderSecundario
        }
    }
}