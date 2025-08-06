open class Pessoa {

    var nome: String = ""
        private set

    fun apresentar(): String {
        return "Olá, meu nome é $nome."
    }

    fun setNome(nome: String) {
        if (nome.length >= 2) {
            this.nome = nome
        }
    }
}