package pt3

fun main() {

    val animal = Animal()
    animal.nome = "Desconhecido"
    println(animal.reproduzirSom())

    println("----------------CACHORRO----------------")
    val cachorro = Cachorro()
    cachorro.nome = "Marley"
    println(cachorro.reproduzirSom())

    println("----------------GATO--------------------")
    val gato = Gato()
    gato.nome = "Bob"
    println(gato.reproduzirSom())

}