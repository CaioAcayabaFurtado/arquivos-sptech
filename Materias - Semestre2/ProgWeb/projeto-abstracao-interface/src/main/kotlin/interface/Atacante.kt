package `interface`

class Atacante:JogadorFutebol, Pessoa() {
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
        println("Opa, não pode pegar a bola com a mão...")
    }

    override fun coletiva(texto: String): String {
        return ("Atacante falando na coletiva: $texto")
    }
}