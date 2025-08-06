
class Cofrinho(
    var objetivo: String,
    var saldo: Double = 0.0,
    var meta: Double = 0.0
) {

    fun depositar(valorDeposito: Double) {
        if (valorDeposito > 0) {
            saldo = saldo + valorDeposito
//            saldo += valorDeposito // mesma coisa
        }
    }

    fun retirar(valorRetirada: Double) {
        if (valorRetirada > 0 && valorRetirada <= saldo) {
            saldo = saldo - valorRetirada
//            saldo -= valorRetirada // mesma coisa
        }
    }

    fun porcentagemAteMeta(): Double {
        val porcentagem = (saldo / meta) * 100.0
        return porcentagem
    }

    fun descrever(): String {
        val porcentagem = porcentagemAteMeta()

        if (porcentagem >= 100) {
            return "O cofrinho de objetivo $objetivo está com R$$saldo de saldo. Já chegou na meta, estando em $porcentagem% dela!"
        }
        return "O cofrinho de objetivo $objetivo está com R$$saldo de saldo, estando a $porcentagem% da meta de R$$meta"
    }

}