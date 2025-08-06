class Cofrinho (
    var objetivo:String = "",
    var meta:Double = 0.0,
    var saldo:Double = 0.0,
    var depositos:Int = 0,

) {
    var valorDeposito:Double = 0.0
    fun depositar(){
        if (valorDeposito > 0.0){
            saldo = saldo + valorDeposito
            depositos++
            println("Valor depositado com sucesso!")
            porcentagemAteMeta()
        } else{
            println("Valor inválido.")
        }
    }
    var valorRetirada:Double = 0.0
    fun retirar(){
        if (valorRetirada > 0.0 && valorRetirada <= saldo){
            saldo = saldo - valorRetirada
            println("Valor retirado com sucesso!")
            porcentagemAteMeta()
        } else{
            println("Valor inválido.")
        }
    }
    var porcentagem = 0.0
    fun porcentagemAteMeta(){
        porcentagem = (saldo/meta)*100
    }
    fun descrever(){
        if (porcentagem < 100.0){
            porcentagemAteMeta()
            println("O cofrinho de objetivo $objetivo está com R$$saldo de saldo, estando a $porcentagem% da meta de R$$meta")
        } else{
            porcentagemAteMeta()
            println("O cofrinho de objetivo $objetivo está com R$$saldo de saldo. Já chegou na meta, estando em $porcentagem% dela!")
        }
    }
}

//variaveis que foram criadas em cima da funcao
//atualizar a porcentagem no depositar e retirar, e chamar a porcentagemAteMeta() apenas para receber o valor da porcentagem