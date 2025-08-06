fun main() {
    val estado = Estado()

//    estado.nome = "Bahia"
    estado.setarNome("Ba")
    estado.populacao = 7_000_000
    estado.uf = "ba"

    println("Nome: ${estado.nome}")
    println("Populacao: ${estado.populacao}")
    println("UF: ${estado.uf}")
    println("UF: ${estado.retornaUf()}")
}