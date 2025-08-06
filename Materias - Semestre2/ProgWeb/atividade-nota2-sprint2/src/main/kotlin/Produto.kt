class Produto {

    //PARTE 1
    var id:Int=0
    var nome:String=""
        private set
    var categoria:String=""
        private set
    var preco:Double=0.0
        private set
    var fabricante:String=""
        private set

    //PARTE 2
    fun setNome(novoValor:String){
        if(novoValor.length>3){
            nome=novoValor
        }else{
            println("Nome inválido! Deve ter mais de 3 caracteres.")
        }
    }
    fun setCategoria(novoValor: String){
        if(novoValor.length>5){
            categoria=novoValor
        }else{
            println("Categoria inválida! Deve ter mais de 5 caracteres.")
        }
    }
    fun setPreco(novoValor: Double){
        if(novoValor>0){
            preco=novoValor
        }else{
            println("Preço inválido! Deve ser maior que 0.")
        }
    }
    fun setFabricante(novoValor: String){
        if(novoValor.length>=2){
            fabricante=novoValor
        }else{
            println("Fabricante inválido! Deve ter mais de 2 caracteres.")
        }
    }
}