package classe_abstrata

fun main() {
    val cachorro = Cachorro()
    cachorro.nome = "renatao"
    cachorro.respirar()
    cachorro.latir()
    cachorro.comer()
    cachorro.nascer()
    val peixe = Peixe()
    peixe.nome = "renatao"
    peixe.respirar()
    peixe.comer()
    peixe.nascer()
}