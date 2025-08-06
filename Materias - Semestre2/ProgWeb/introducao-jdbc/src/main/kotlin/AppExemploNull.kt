fun main() {
    val nome:String? = null

    val resposta1:String = nome?: "Nome não definido"
    // se for nulo, o resultado é ^^^^^^^^^^^^^^^^^
    // se não, recebe o nome
    println(resposta1)

    // exemplo com if else
    var resposta2 = ""
    if (nome != null){
        resposta2=nome
    } else{
        resposta2="Nome não definido"
    }
    println(resposta2)

    // --------------------------------------------------------------------

    print("Digite seu email:")
    //se for null, coloca o comentario
    var email:String = readlnOrNull()?:"nao informado"
    print(email)


    println("Digite um número:")
    //se tiver algo, tenta converter para inteiro, se nao conseguir, vira 0
    var numero:Int = readln().toIntOrNull()?:0
    println(numero)

    println("Não digite um número:")
    var numero2:Int = readLine()!!.toInt()
    println(numero2)

    //----------------------------------------------------------------------

    val musica: Musica? = null
    if (musica!=null){
        println(musica.nome)
        println(musica.produtor?:"sem produtor")
    }else{
        println("musica nao existe")
    }


}


