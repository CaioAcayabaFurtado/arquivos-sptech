fun main() {

    // lista imutavel
    val listaPaises = listOf("Brasil", "Alemanha", "EUA")
    println("Paises $listaPaises")
    println("Primeiro valor: ${listaPaises.get(0)}")

    val listaProdutos = mutableListOf("Bolacha", "Macarrão")
    listaProdutos.add("Sabonete")
    listaProdutos.remove("Bolacha") //removendo pelo elemento
    listaProdutos.removeAt(0) // removendo pela posição
    println("$listaProdutos")

    // tipo declarativo por atribuição = é possivel criar uma lista vazia
    val listaInteiros = mutableListOf<Int>()
    // tipo declarativo por declaração
    // val listaInteirosDeclarativa = MutableList()<Int>= mutableListOf()

    // Independente do valor que receba ele vai ser imutavel
    // val listaInteirosDeclarativaSemAlteracao = List()<Int>

    val listaPessoa = mutableListOf("João", "Maria", "Jose")
    println("A primeira pessoa ${listaPessoa[0]}")
    println("A primeira pessoa ${listaPessoa.get(0)}")
    println("A primeira pessoa ${listaPessoa.first()}")
    println("A ultima pessoa ${listaPessoa.last()}")
    // ultima pessoa pegando o ultimo index pelo tamanho da lista
    // println("A ultima pessoa ${listaPessoa[listaPessoa.size - 1]}")

    // validando se o indice existe
    println("A quinta pessoa ${listaPessoa.indices.contains(4)}")
    // validando se o valor existe
    println("A pessoa existe? ${listaPessoa.contains("Maria")}")
    // ou utilizar "if"

    println("Pessoas $listaPessoa")
    println("A partir da segunda pessoa: ${listaPessoa.drop(1)}")
    println("A partir da penultima pessoa: ${listaPessoa.dropLast(1)}")
    println("A sem a primeira e ultima pessoa: ${listaPessoa.drop(1).dropLast(1)}")

    listaPessoa.add("Fernando")
    listaPessoa.add("Ezequiel")
    listaPessoa.removeAt(0)
    println("$listaPessoa")

    // atualizando pelo valor do elemento
    listaPessoa[0] = "Mariazinha"
    listaPessoa.set(0, "Mariazinhaaaa")


}