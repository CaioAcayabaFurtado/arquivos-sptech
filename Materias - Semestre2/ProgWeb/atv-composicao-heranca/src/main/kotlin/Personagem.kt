class Personagem: Pessoa(){
    private val habilidades = mutableListOf<Habilidade>()
    fun adicionarHabilidade(habilidade: Habilidade){
        habilidades.add(habilidade)
    }
    fun listarHabilidades():List<Habilidade>{
        return habilidades
    }
    fun removerHabilidade(nomeHabilidade: String){
        habilidades.removeIf { it.nome == nomeHabilidade }
    }
}