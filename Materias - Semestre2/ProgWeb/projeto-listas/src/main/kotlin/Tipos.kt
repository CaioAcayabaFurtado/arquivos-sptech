fun main() {

    val textoConst = "texto inicial"

    // tipo inferido (tipo é definido pelo valor inicial)
    var texto = "texto inicial"

    // tipo declarativo (tipo definido pela declaração da variavel)
    var textoDefinido: String = "1"

    val numero: Int = 10 // pode ter valor inicial ou não (quando é declarativo)
    val real: Double = 10.0 // pode ter valor inicial ou não (quando é declarativo)

    var aposentado: Boolean // pode ter valor inicial ou não (quando é declarativo)
    aposentado = true
    aposentado = false

    val aposentadoTexto = aposentado.toString() // converter Boolean para string
    val decimalParaInteiro = real.toInt() // converter Double para Int
}