fun main() {
    val musicaRepositorio = MusicaRepositorio()
    musicaRepositorio.configurar()
    musicaRepositorio.criarTabela()

    val novaMusica = Musica()
    novaMusica.nome = "Diggy Diggy Hole"
    novaMusica.banda = "Rock de anão"
    novaMusica.produtor = "Produtora Top"

    // retorna "true" ou "false"
    val sucesso = musicaRepositorio.inserir(novaMusica)

    if (sucesso) {
        println("Inserido com sucesso!")
    } else {
        println("Deu ruim!")
    }

    val listaMusicas = musicaRepositorio.listar()

    listaMusicas.forEach { daVez ->
        println("""
            Id: ${daVez.id}
            Nome: ${daVez.nome}
            Banda: ${daVez.banda}
            Produtor: ${daVez.produtor}
        """.trimIndent())
    }

    val idParaBusca = 1

    if (musicaRepositorio.existePorId(idParaBusca)) {
        val musicaEncontrada = musicaRepositorio.buscaPorId(idParaBusca)
        println("Nome: ${musicaEncontrada.nome}")
    } else {
        println("Música não existe!")
    }

    // INSERINDO MÚSICA 2
    musicaRepositorio.inserir(
        Musica(nome = "Hotel California", banda = "Eagles", produtor = "TOP")
    )

    if (musicaRepositorio.existePorId(idParaBusca)) {
        val deletado = musicaRepositorio.deletaPorId(idParaBusca)
    } else {
        println("Música não existe!")
    }

    if (musicaRepositorio.existePorId(2)) {
        val musicaAtualizada = Musica(
            nome = "Californication",
            banda = "RHCP",
            produtor = "Na california"
        )
        val atualizado = musicaRepositorio.atualizaPorId(2, musicaAtualizada)
        println(if (atualizado) "Atualizado!" else "Deu ruim!") // if de uma linha só
    } else {
        println("Música não existe!")
    }

    println("----- Exibição geral -----")
    val listagemAtualizada = musicaRepositorio.listar()

    listagemAtualizada.forEach { daVez ->
        println("""
            Id: ${daVez.id}
            Nome: ${daVez.nome}
            Banda: ${daVez.banda}
            Produtor: ${daVez.produtor}
        """.trimIndent())
    }

}