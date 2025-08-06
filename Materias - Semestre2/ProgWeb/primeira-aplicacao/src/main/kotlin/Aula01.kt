fun main() {

    println("Oi no Kotlin")
    println("Oi 2")

    println("------------------------------------------------------------------------------------------------------------------------------------------")

    val nome = "Lucas" // "val" não pode ser alterado, diferente de "var"
    // var nome = "Lucas"
    // var nome: String = "Lucas"

    println("Bom dia $nome")
    println("Bom dia ${nome.uppercase()}")

    println("------------------------------------------------------------------------------------------------------------------------------------------")

    val salario = 200

    // if normal
    if (salario < 1500) {
        println("Um salario minimo")
    } else {
        println("Um salario não minimo")
    }

    println("------------------------------------------------------------------------------------------------------------------------------------------")

    val nota = 6.0

    // if de uma linha so
    val resultado = if (nota < 6.0) "Reprovado" else "Aprovado"
    println("$resultado")

    /* if(nota < 3){
        println("Deu ruim")
    } else if(nota < 6){
        println("Quase")
    } else {
        println("Deu bom")
    } */

    // switch case
    when (nota) {
        1.0 -> println("Deu muito ruim!")
        3.0 -> println("Deu ruim!")
        in 3.1..6.0 -> println("Melhore!")
        else -> println("Deu bom!")
    }

    var resultadoNota = when (nota) {
        1.0 -> "Deu muito ruim!"
        3.0 -> "Deu ruim!"
        in 3.1..6.0 -> "Melhore!"
        else -> "Deu bom!"
    }
    println(resultadoNota)

    println("------------------------------------------------------------------------------------------------------------------------------------------")

    var contador = 0

    while (contador < 10) {
        println("número ${++contador}") // pre incremento
    }

    println("Contagem pelo For:")

    for (n in 1..10) {
        println(n)
    }

    println("Contagem inversa pelo For:")

    for (n in 10 downTo 1) {
        println(n)
    }
}