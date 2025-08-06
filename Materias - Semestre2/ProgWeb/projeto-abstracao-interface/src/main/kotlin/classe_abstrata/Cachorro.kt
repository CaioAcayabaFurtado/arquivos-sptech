package classe_abstrata

class Cachorro: Animal() {
    fun latir(){
        println("$nome está latindo.")
    }
    override fun comer(){
        println("Comendo ração")
    }
    override fun nascer() {
        println("Nascendo do ventre de sua mãe")
    }

}