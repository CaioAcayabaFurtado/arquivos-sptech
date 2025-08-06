import java.util.*
import kotlin.math.pow

fun main(){

    // Scanner's
    val snTexto = Scanner(System.`in`)
    val snNumero = Scanner(System.`in`)

    // Captura de dados
    print("Digite o seu nome:")
    val nome = snTexto.nextLine()
    print("Digite o seu peso:")
    val peso = snNumero.nextDouble()
    print("Digite a sua altura:")
    val altura = snNumero.nextDouble()

    // Exibição
    val imc: Double = peso/(altura.pow(2))
    println("Olá $nome! Seu IMC é de ${"%.2f".format(imc)}")
}