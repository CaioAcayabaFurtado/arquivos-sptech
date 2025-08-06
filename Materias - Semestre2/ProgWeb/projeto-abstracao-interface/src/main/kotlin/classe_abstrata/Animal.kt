package classe_abstrata

abstract class Animal {
    var nome:String=""
    open fun respirar(){
        println("Animal está recebendo oxigenio e mandando gás carbonico")
    }
    abstract fun comer()
    abstract fun nascer()
}
