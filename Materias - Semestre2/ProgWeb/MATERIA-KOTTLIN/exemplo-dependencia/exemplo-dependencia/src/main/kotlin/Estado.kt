class Estado {

    var nome: String = ""
        set(value) {
            if (value.length >= 4) {
                field = value
            }
        }
        get() = field.uppercase()

    var populacao: Int = 0
        set(value) {
            if (value > 0) {
                field = value
            }
        }

    var uf: String = ""
        set(value) {
            if (value.length == 2) {
                field = value
            }
        }
        get() = field.uppercase()

    // não consigo setar sem aplicar a regra
    fun setarNome(valor: String) {
        nome = valor
    }

    // não consigo retornar sem usar o "get" formatado
    fun retornaUf(): String {
        return uf
    }
}