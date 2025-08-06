package classe_abstrata

class Peixe: Animal() {
    override fun comer() {
        println("Está beliscando o alimento")
    }
    override fun respirar() {
        println("Está respirando agua.")
    }
    override fun nascer() {
        println("Nascendo de uma ova")
    }
}