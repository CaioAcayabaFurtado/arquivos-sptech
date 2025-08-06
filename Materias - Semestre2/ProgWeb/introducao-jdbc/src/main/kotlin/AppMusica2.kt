fun main() {
    val musicaRepositorio = MusicaRepositorio()
    musicaRepositorio.configurar()
    musicaRepositorio.criarTabela()

    val novaMusica = Musica()
    novaMusica.nome = "Diggy Diggy Hole"
    novaMusica.banda = "Rock de anão"
    novaMusica.produtor = "Produtora top"

    val sucesso = musicaRepositorio.inserir(novaMusica)
    if (sucesso){
        println("Inserido com sucesso!")
    } else{
        println("Deu ruim!")
    }

    val listaMusicas = musicaRepositorio.listar()
    listaMusicas.forEach {
        println(
            """
            ---------------------------
            id: ${it.id}
            nome: ${it.nome}
            banda: ${it.banda}
            produtor: ${it.produtor}
        """.trimIndent()
        )
    }

    val idParaBusca = 1
    if (musicaRepositorio.existePorId(idParaBusca)){
        val musicaEncontrada = musicaRepositorio.buscarPorId(idParaBusca)
        println("Nome: ${musicaEncontrada.nome}")
    }else{
        println("Musica nao existe!")
    }

    //inserindo musica 2
    musicaRepositorio.inserir(
        Musica(nome = "Hotel California", banda = "Eagles", produtor = "top")
    )

    // deletando
    if (musicaRepositorio.existePorId(idParaBusca)){
        val deletado = musicaRepositorio.deletaPorId(idParaBusca)
    }else{
        println("Musica nao existe!")
    }

    // alterando
    if (musicaRepositorio.existePorId(2)){
        val musicaAtualizada = Musica(
            nome = "Californication",
            banda = "RHCP",
            produtor = "Na california"
        )
    }else{
        println("Musica nao existe!")
    }
    
}
