
fun main() {

    println("Bem-vindo!")
    print("Digite o objetivo do cofrinho: ")
    val objetivo = readln()

    print("Digite a meta do cofrinho: ")
    val meta = readln().toDouble()

    val cofrinho = Cofrinho(objetivo = objetivo, meta = meta)

    while(true) {

        println("------- Cofrinho --------")
        println("1 - Depositar no cofre.")
        println("2 - Retirar do cofre.")
        println("3 - Detalhes.")
        println("4 - Sair.")
        print("Qual opção desejada: ")
        val opcaoEscolhida = readln().toInt()

        when(opcaoEscolhida) {
            1 -> {
                print("Digite o valor a ser depositado: ")
                val deposito = readln().toDouble()
                cofrinho.depositar(deposito)
            }
            2 -> {
                print("Digite o valor a ser retirado: ")
                val valorSerRetirado = readln().toDouble()
                cofrinho.retirar(valorSerRetirado)
            }
            3 -> {
                val texto = cofrinho.descrever()
                println(texto)
            }
            4 -> break
        }

    }



}