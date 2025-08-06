class Pessoa(
    var nome: String,
    var idade:Int
) {
    fun respirar(){
        println("Respirando...")
    }
    fun ola(){
        println("Olá, meu nome é $nome e tenho $idade anos")
    }
}