class Missao {
    var nome:String=""
        private set
    var descricao:String=""
        private set
    var nivelDificuldade:Int=0
        private set
    fun setNome(novoValor:String){
        if (novoValor.length>=3){
            nome = novoValor
        }
    }
    fun setDescricao(novoValor:String){
        if (novoValor.length>=10){
            descricao = novoValor
        }
    }
    fun setNivelDificuldade(novoValor: Int){
        if (novoValor > 0){
            nivelDificuldade = novoValor
        }
    }
}