fun main() {
    println("Olá no Kotlin")
    println("Opa!!")

//    var nome = "Lucas" // var pode ser alterado (mutavel) = equivalente a var no JS
    val nome = "Lucas" // val não pode ser alterado (imutavel) = equivalente a const no JS

    // não pode ser alterado
//    nome = "João"

    println("Bom dia " + nome)
    println("Bom dia $nome")
    println("Bom dia ${nome.uppercase()}")

    val salario = 2000

    // Igual qualquer outro "if"
    if (salario < 1500) {
        println("Um salário mínimo")
    } else {
        println("Come bem")
    }

    val nota = 6.0
    // "if de uma linha só"
    val resultado = if (nota < 6.0) "Reprovado" else "Aprovado"
    println("Resultado $resultado")

    if (nota < 3) {
        println("Deu ruim!")
    } else if (nota < 6) {
        println("Passou raspando!")
    } else {
        println("Pode até escolher o estágio")
    }

    // Switch case - verifica como se fosse if, else if para "printar" resultados
    when(nota) {
        1.0 -> println("Deu ruim pra tu")
        3.0 -> println("Vai levar carcada")
        in 3.1..6.0 -> println("Da pra se esforçar")
        else -> println("Deu bom")
    }

    println("----------------------------------------------------------------------------------------------------------------------------------")

    val nota2 = 6.0

    val resultadoNota = when(nota2) {
        in 0.0..3.0 -> println("Deu ruim pra tu")
        in 3.1..6.0 -> println("Da pra se esforçar")
        in 6.1..10.0 -> println("Aprovadissimo")
        else -> println("Nota inválida")
    }

    println(resultadoNota)

    var contador = 0

    while(contador < 10) {
        println("número: ${++contador}") // pré-incremento
        // alternativa mais comum
//        contador++
//        println("número: ${contador}")
    }

//    for(var i = 0; i < 11; i++)

    println("------------- FOR")
    println("1 a 10")
    for (n in 1..10) {
        println(n)
    }
    println("10 a 1")
    for (n in 10 downTo 1) {
        println(n)
    }
}
