package composicao

fun main() {
    val casa = Casa()
    val quarto = Quarto()
    quarto.metrosQuad=12.0
    casa.adicionarQuarto(quarto)
    casa.listarQuartos().forEach(){
        println(quarto.metrosQuad)
    }
}