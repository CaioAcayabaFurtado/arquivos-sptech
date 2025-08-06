package `interface`

class Goleiro: JogadorFutebol, Pessoa() {
    override fun correr() {
        println("Correndo...")
    }

    override fun chutar() {
        println("Chutando...")
    }

    override fun cair() {
        println("Caindo...")
    }

    override fun pegarComMao() {
        println("Defendendo...")
    }

    override fun coletiva(texto: String): String {
        return ("Goleiro falando na coletiva: $texto")
    }
}