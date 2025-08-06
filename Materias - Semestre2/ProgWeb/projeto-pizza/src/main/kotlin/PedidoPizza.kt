class PedidoPizza (
    var sabor:String = "",
    var preco:Double = 0.0,
    var quantidadeAmigos:Int = 0
){
    fun descrever(){
        println("Pedido de pizza de $sabor, que custa R$$preco")
    }

    var cupom: String = ""
    var listaCupom = mutableListOf<String>()
    fun validarCupom() {
        listaCupom.add(cupom)

        if (cupom == "#amopizza") {
            preco = preco * 0.9
        }
    }

    var precoAmigo = 0.0
    fun obterValorPorAmigo(){
        precoAmigo = (preco/quantidadeAmigos).toDouble()
    }

    fun obterCuponsUsados(){
        for (i in 1..listaCupom.size){
            println("- ${listaCupom[(i-1)]}\n")
        }
    }
}