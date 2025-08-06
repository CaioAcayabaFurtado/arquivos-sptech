class Personagem : Pessoa() {

    private val habilidades = mutableListOf<Habilidade>()

    fun adicionarHabilidade(habilidade: Habilidade) {
        habilidades.add(habilidade)
    }

    fun removerHabilidade(nome: String) {
        habilidades.removeIf{ it.nome == nome}
    }

    fun listarHabilidades(): List<Habilidade> {
        return habilidades.toList()
    }


}