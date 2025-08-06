class Carro (
    var marca: String,
    var modelo: String,
    var velocidade: Int = 0,
    var ligado: Boolean = false
){

    val historicoDeAcao = mutableListOf<String>()

    fun ligar(){
        ligado = true
        historicoDeAcao.add("Ligou o carro.")
    }
    fun desligar(){
        ligado = false
        historicoDeAcao.add("Desligou o carro.")
    }

    fun acelerar(){
        if (ligado) {
            velocidade += 10
            if (velocidade >= 120) {
                velocidade = 120
            }
            historicoDeAcao.add("Acelerou, velocidade atual: $velocidade.")
        }
    }

    fun frear(){
        if (ligado) {
            velocidade -= 10
            if (velocidade <= 0) {
                velocidade = 0
            }
            historicoDeAcao.add("Freou, velocidade atual: $velocidade.")

        }
    }

    fun descricao():String{

        val textoLigado = if (ligado){
            "Ligado"
        } else{
            "Desligado"
        }

        return"""
            
            Marca: $marca
            Modelo: $modelo
            Ligado: $textoLigado
            Velocidade: $velocidade Km/h
            
        """.trimIndent()
    }

    fun caixaPreta():String{

        var acoes=""
        for(acaoDaVez in historicoDeAcao){
            acoes += "\n - $acaoDaVez"
        }
        return acoes
    }

    fun exibirVelocidadeComTexto(texto:String){
        println("Texto da Velocidade dinâmico: $texto")
    }
}