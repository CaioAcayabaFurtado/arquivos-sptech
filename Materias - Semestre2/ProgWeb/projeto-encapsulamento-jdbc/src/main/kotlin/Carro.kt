class Carro {

    var id:Int = 0
    var nome:String = ""
        private set
    var fabricante:String = ""
        private set
    var potencia:Int = 0
        private set

    // regras
    fun setNome(novoValor:String){
        if (novoValor.length>3){
            nome=novoValor
        }
    }
    fun setFabricante(novoValor: String){
        if (novoValor.length>3){
            fabricante=novoValor
        }
    }
    fun setPotencia(novoValor: Int){
        if (novoValor>0){
            potencia=novoValor
        }
    }
}