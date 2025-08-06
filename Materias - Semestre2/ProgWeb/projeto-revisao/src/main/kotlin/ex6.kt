import java.util.Scanner

fun main() {
    val snNumero = Scanner(System.`in`)
    val snTexto = Scanner(System.`in`)

    print("Digite o nome da linha:")
    val nome = snTexto.nextLine()
    print("Digite o maior tempo de volta:")
    val maior= snNumero.nextInt()
    print("Digite o menor tempo de volta:")
    val menor = snNumero.nextInt()

    val media = (maior + menor) / 2

    println("Na linha $nome o tempo médio p/volta é $media")
}