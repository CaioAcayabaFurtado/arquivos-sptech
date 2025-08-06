class Carro {

    var id: Int = 0

    // private antes do var engloba "get" e "set"
//    private var nome: String = ""
    var nome: String = ""
        private set

    var fabricante: String = ""
        private set

    var potencia: Int = 0
        private set

    fun setNome(novoValor: String) {
        if (novoValor.length > 3) {
            nome = novoValor
        } else {
            println("Valor incorreto! Digite mais de 3 caracteres")
        }
    }

    fun setFabricante(novoValor: String) {
        if (novoValor.length > 3) {
            fabricante = novoValor
        } else {
            println("Valor incorreto! Digite mais de 3 caracteres")
        }
    }

    fun setPotencia(novoValor: Int) {
        if (novoValor > 0) {
            potencia = novoValor
        } else {
            println("Valor incorreto! Digite maior que 0")
        }
    }
}