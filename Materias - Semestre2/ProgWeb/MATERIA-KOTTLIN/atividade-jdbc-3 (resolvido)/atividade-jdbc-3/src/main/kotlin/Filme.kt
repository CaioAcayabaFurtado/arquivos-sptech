class Filme {

    var id: Int = 0
    var nome: String = ""
        private set
    var genero: String = ""
        private set
    var anoLancamento: Int = 0
        private set
    var duracao: Int = 0 // número de minutos
        private set
    var alugado: Boolean = false
        private set

    fun setNome(novoValor: String) {
        if (novoValor.length >= 2) { // deve ter no mínimo 2 caracteres para ser válido
            nome = novoValor
        }
    }

    fun setGenero(novoValor: String) {
        if (novoValor.length >= 2) { // deve ter no mínimo 2 caracteres para ser válido
            genero = novoValor
        }
    }

    fun setAnoLancamento(novoValor: Int) {
        if (novoValor >= 1895) { // o primeiro filme foi em 1895
            anoLancamento = novoValor
        }
    }

    fun setDuracao(novoValor: Int) {
        if (novoValor >= 15) { // a duração deve ser maior ou igual a 15 minutos
            duracao = novoValor
        }
    }

    fun setAlugado(novoValor: Boolean) {
        alugado = novoValor
    }

    fun descrever(): String { // retorna uma string com os dados do filme
        return """
            Id: ${id}
            Nome do Filme: ${nome}
            Gênero do Filme: ${genero}
            Ano de Lançamento: ${anoLancamento}
            Duração: ${converterDuracaoEmHoras()}
            Alugado: ${if (alugado) "Sim" else "Não"}
        """.trimIndent()
    }

    // retorna duração em "horas" e "minutos"
    // digamos que tenho 2,5 de duração e precisa virar 2:30

    fun converterDuracaoEmHoras(): String { // retorna uma string com a duração formatada
        val horas = duracao / 60 // divisão inteira (pegar as horas)
        val minutos = duracao % 60 // resto da divisão (pegar os minutos)
        return "${horas.toString().padStart(2, '0')}:${minutos.toString().padStart(2, '0')}" // exibir sempre com 2 dígitos
    }
}