import java.util.*

fun main() {
    val snNumero = Scanner(System.`in`)

    print("Digite a sua idade:")
    val idade = snNumero.nextInt()

    when{
        idade >= 16 -> println("Já pode votar.")
        idade < 16 -> println("Não pode votar.")
    }
}