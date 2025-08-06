package pt4

fun main() {
    val funcionario = Funcionario()
    println("----------GERENTE----------")
    val gerente = Gerente()
    gerente.nome = "Claudio"
    gerente.salario = 5000.0
    println(gerente.calcularBonificacao())

    println("----------DESENVOLVEDOR----")
    val dev = Desenvolvedor()
    dev.nome = "João"
    dev.salario = 3000.0
    println(dev.calcularBonificacao())
}