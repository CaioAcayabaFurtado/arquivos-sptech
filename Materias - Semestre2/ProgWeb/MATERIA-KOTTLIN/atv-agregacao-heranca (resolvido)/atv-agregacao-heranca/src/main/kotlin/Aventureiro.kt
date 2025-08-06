class Aventureiro {

    var nome: String = ""
        private set
    var energia: Int = 0
        private set

    fun setNome(novoValor: String) {
        if (novoValor.length >= 3) {
            nome = novoValor
        }
    }

    fun setEnergia(novoValor: Int) {
        if (novoValor > 0) {
            energia = novoValor
        }
    }

    fun descansar() {
        energia += 10
    }

    fun status(): String {
        return "Aventureiro $nome tem $energia de energia."
    }
}