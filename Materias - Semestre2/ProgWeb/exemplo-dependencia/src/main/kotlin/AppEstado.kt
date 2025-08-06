fun main() {
    val estado=Estado()
    //estado.nome="Bahia"
    estado.setarNome("Ba")
    estado.populacao=7_000_000
    estado.uf="BA"
    println(estado.nome)
    println(estado.populacao)
    println(estado.uf)
}