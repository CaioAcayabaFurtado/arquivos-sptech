fun main() {
    val carro1 = Carro()
    carro1.setNome("Uno Mile")
    carro1.setFabricante("Fiat")
    carro1.setPotencia(450)

    println("""
        Nome: ${carro1.nome}
        Fabricante: ${carro1.fabricante}
        Potência: ${carro1.potencia}
    """.trimIndent())
}