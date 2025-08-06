class Explorador: Aventureiro() {
    var nivelExperiencia:Int = 0
        private set
    private val missoes = mutableListOf<Missao>()

    fun inscreverEmMissao(missao: Missao){
        missoes.add(missao)
    }
    fun listarMissoes():List<Missao>{
        return missoes
    }
    fun abandonarMissao(nomeMissao:String){
        missoes.removeIf { it.nome == nomeMissao }
    }
    fun ganharExperiencia(pontos:Int){
        nivelExperiencia = nivelExperiencia+pontos
    }
    fun dificuldadeMediaMissoes():Int{
        if (missoes.size > 0){
            val somaNiveis = missoes.sumOf { it.nivelDificuldade }
            val qtdMissoes = missoes.size
            val media = somaNiveis/qtdMissoes
            return media
        }
        return 0
    }
}