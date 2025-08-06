class Explorador {

    var nivelExperiencia: Int = 0
        private set
    private val missoes = mutableListOf<Missao>()

    fun inscreverEmMissao(missao: Missao) {
        missoes.add(missao)
    }

    fun listarMissoes(): List<Missao> {
        return missoes
    }

    fun abandonarMissao(nomeMissao: String) {
        // jeito otimizado
        missoes.removeIf { it.nome == nomeMissao }

        // jeito 2
//        val missao = missoes.find { it.nome == nomeMissao }
//        if (missao != null) {
//            missoes.remove(missao)
//        }
    }

    fun ganharExperiencia(pontos: Int) {
        nivelExperiencia += pontos
    }
}