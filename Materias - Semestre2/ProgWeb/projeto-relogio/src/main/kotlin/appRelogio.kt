import java.util.Scanner
fun main() {

    val snString = Scanner(System.`in`)

    val relogio = Relogio(
        "Rolex",
        230000.00,
        12,
        24,
        32
    )
    println(relogio.descricaoRelogio())
    println(relogio.verHora())
    relogio.zerar()
    println(relogio.verHora())

    println("--------------------------------------------------------------------------------------")

    val relogio2 = Relogio(modelo = "", preco = 0.0, horas = 0, minutos = 0, segundos = 0)
    println("Digite o modelo:")
    relogio2.modelo = snString.nextLine()
    println("Digite o preço:")
    relogio2.preco = snString.nextDouble()
    println("Digite as horas:")
    relogio2.horas = snString.nextInt()
    println("Digite os minutos:")
    relogio2.minutos = snString.nextInt()
    println("Digite os segundos:")
    relogio2.segundos = snString.nextInt()

    println("---------------------------")
    println(relogio2.descricaoRelogio())
    println(relogio2.verHora())

}