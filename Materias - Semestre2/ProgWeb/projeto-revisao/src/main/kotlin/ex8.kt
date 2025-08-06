import java.util.Scanner

fun main(){
    val snNumero = Scanner(System.`in`)
    print("Digite o seu salário:")
    val sal = snNumero.nextDouble()

    when{
        sal in 0.0 .. 1000.0 -> println("Classe C")
        sal in 1000.01 .. 2000.0-> println("Classe B")
        sal in 2000.01.. 5000.0 -> println("Classe A")
        sal >5000 -> println("Classe AA")
    }
}