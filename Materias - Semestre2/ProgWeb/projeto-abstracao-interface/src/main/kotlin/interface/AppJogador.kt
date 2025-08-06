package `interface`

fun main() {
    val atacante = Atacante()
    atacante.correr()
    atacante.chutar()
    atacante.cair()
    atacante.pegarComMao()
    println(atacante.coletiva("oi"))

    val goleiro = Goleiro()
    goleiro.correr()
    goleiro.chutar()
    goleiro.cair()
    goleiro.pegarComMao()
    println(goleiro.coletiva("tchau"))

}