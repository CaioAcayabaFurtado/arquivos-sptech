package pt4

class Gerente:Funcionario() {
    override fun calcularBonificacao():String {
        var bonificacao = ((salario/100)*20)
        salario = salario + bonificacao
        return """
            Salario ajustado para: $salario
            Bonificacao total: $bonificacao
        """.trimIndent()
    }
}