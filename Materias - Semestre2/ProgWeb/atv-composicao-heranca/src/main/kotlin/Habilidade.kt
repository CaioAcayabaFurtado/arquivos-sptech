class Habilidade{
    var nome:String=""
        private set
    var poder:Int=0
        private set
    fun setNome(valor:String){
        if (valor.length>=2){
            nome=valor
        }
    }
    fun setPoder(valor:Int){
        if (valor>0){
            poder=valor
        }
    }
}
