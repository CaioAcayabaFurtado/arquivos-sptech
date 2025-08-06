import java.time.LocalDate

class Produto(
    var nome:String="",
    var preco:Double=0.0,
    var validade: LocalDate = LocalDate.now(),
    ) {
    fun descrever():String{
        //val hoje = LocalDate.of(2024,9,9)
        val hoje = LocalDate.now()
        if(validade.isBefore(hoje)){
            return "Produto $nome está vencido!"
        }else{
            return "Da pra mandar pra dentro!"
        }
    }
}