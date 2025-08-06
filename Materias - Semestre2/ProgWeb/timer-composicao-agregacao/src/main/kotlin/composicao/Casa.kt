package composicao

class Casa {
    private val quartos = mutableListOf<Quarto>()
    fun adicionarQuarto(novoQuarto: Quarto){
        quartos.add(novoQuarto)
    }
    fun listarQuartos():List<Quarto>{
        return quartos
    }
}