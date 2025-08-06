fun main() {
    val carro1 = Carro()

    // na criação, não passa id
//    carro1.nome = "Uno Mile"
//    carro1.fabricante = "Fiat"
//    carro1.potencia = 75

    carro1.setNome("Uno Mile")
    carro1.setFabricante("Fiat")
    carro1.setPotencia(450)

    println("""
        Nome: ${carro1.nome}
        Fabricante: ${carro1.fabricante}
        Potência: ${carro1.potencia}
    """.trimIndent())
}