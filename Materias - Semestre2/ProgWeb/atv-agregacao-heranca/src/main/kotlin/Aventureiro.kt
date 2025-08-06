open class Aventureiro {
    var nome:String=""
        private set
    var energia:Int=0
        private set
    fun descansar(){
        energia = energia + 10
    }
    fun status():String{
        return "Aventureiro $nome tem $energia de energia."
    }
    fun setNome(novoValor:String){
        if (novoValor.length>=3){
            nome = novoValor
        }
    }
    fun setEnergia(novoValor: Int){
        if (novoValor > 0){
            energia = novoValor
        }
    }
}