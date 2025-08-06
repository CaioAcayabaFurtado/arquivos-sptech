package pt4

open class Funcionario {
    var nome:String=""
    var salario:Double=0.0
    open fun calcularBonificacao():String{
        var bonificacao = ((salario/100)*10)
        salario = salario + bonificacao
        return """
            Salario ajustado para: $salario
            Bonificacao total: $bonificacao
        """.trimIndent()
    }
}