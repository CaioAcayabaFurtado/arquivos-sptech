class HeroiSobrenatural: Heroi() {
    var poderSecundario:String=""
        private set

    fun setPoderSecundario(novoValor:String){
        if (novoValor.length>=10){
            poderSecundario=novoValor
        }
    }

    override fun usarPoder(): String {
        return "O herói sobrenatural $nome libera seu poder principal: $poderPrincipal!"
    }

    override fun descrever(): String {
        return "Herói $nome: Poder Principal - $poderPrincipal, Poder Secundário - $poderSecundario, Energia - $nivelEnergia."
    }

}