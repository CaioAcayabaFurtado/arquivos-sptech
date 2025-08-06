import java.util.Scanner



fun main(){
    for (i in 1..3) {
        val nome = perguntaTexto("Digite seu ${i}° nome: ")
        val idade = perguntaNumero("Digite a sua ${i}° idade: ")
        resposta(nome, idade)
    }
}



fun perguntaTexto(nome: String):String{
    val sn = Scanner(System.`in`)
    print(nome)
    return sn.nextLine()
}



fun perguntaNumero(idade: String):Int{
    val sn = Scanner(System.`in`)
    print(idade)
    return sn.nextInt()
}



fun resposta(nome:String, idade:Int) {
    println("Olá $nome! Sua idade é $idade")
}