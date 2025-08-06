class Filme {
    var id:Int=0
    var nome:String=""
    private set
    var genero:String=""
    private set
    var anoLancamento:Int=0
    private set
    var duracao:Int=0
    private set
    var alugado:Boolean=false

    fun setNome(novoValor:String){
        if (novoValor.length>=2){
            nome=novoValor
        }
    }
    fun setGenero(novoValor:String){
        if (novoValor.length>=2){
            genero=novoValor
        }
    }
    fun setAnoLancamento(novoValor:Int){
        if (novoValor>=1895){
            anoLancamento=novoValor
        }
    }
    fun setDuracao(novoValor:Int){
        if (novoValor>=15){
            duracao=novoValor
        }
    }
    fun converterDuracaoEmHoras():String{
        var horas = duracao / 60
        var minutos = duracao % 60
        return """
            ${"%02d".format(horas)}:${"%02d".format(minutos)}
        """.trimIndent()
    }
    fun descrever():String{
        return """
            Id: ${id}
            Nome do Filme: ${nome}
            Gênero do Filme: ${genero}
            Ano de Lançamento: ${anoLancamento}
            Duração: ${converterDuracaoEmHoras()}
            Alugado: ${if (alugado) "Sim" else "Não"}
        """.trimIndent()
    }
}
