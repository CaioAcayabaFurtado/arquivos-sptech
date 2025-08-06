open class Pessoa {
    var nome:String=""
        private set

    fun setNome(valor:String){
        if (valor.length>=2){
            nome=valor
        }
    }
    fun apresentar():String{
        return "Olá, meu nome é ${nome}."
    }
}