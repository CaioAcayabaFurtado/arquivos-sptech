class HeroiTecnologico : Heroi(), UsuarioGadgets {

    var gadgetAtual: String = ""
        private set

    override fun usarPoder(): String {
        return "O herói tecnológico $nome usa seu poder: $poderPrincipal, auxiliado por tecnologia avançada!"
    }

    override fun descrever(): String {
        return "Herói $nome: Poder Principal - $poderPrincipal, Energia - $nivelEnergia."
    }

    override fun equiparGadget(gadget: String): String {
        gadgetAtual = gadget
        return "Gadget $gadgetAtual equipado pelo herói $nome."
    }

    override fun usarGadget(): String {
        return if (gadgetAtual.isNotEmpty()) {
            "O gadget $gadgetAtual foi ativado!"
        } else {
            "Nenhum gadget equipado"
        }
    }
}