package pt4

class Desenvolvedor:Funcionario() {
    override fun calcularBonificacao():String {
        var bonificacao = ((salario/100)*15)
        salario = salario + bonificacao
        return """
            Salario ajustado para: $salario
            Bonificacao total: $bonificacao
        """.trimIndent()
    }
}