abstract class Heroi {
    var nome:String=""
        private set
    var poderPrincipal:String=""
        private set
    var nivelEnergia:Int=0
        private set

    fun setNome(novoValor:String){
        if (novoValor.length>=3){
            nome=novoValor
        }
    }
    fun setPoderPrincipal(novoValor:String){
        if (novoValor.length>=10){
            poderPrincipal=novoValor
        }
    }
    fun setNivelEnergia(novoValor:Int){
        if (novoValor>0){
            nivelEnergia=novoValor
        }
    }

    abstract fun usarPoder():String
    abstract fun descrever():String

    fun recuperarEnergia():String{
        nivelEnergia+=20
        return "O heroi $nome está recuperando energia e agora tem $nivelEnergia pontos."
    }
}