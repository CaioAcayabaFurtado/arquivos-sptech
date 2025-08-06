import java.util.Scanner

fun main(){
    val snNumero = Scanner(System.`in`)
    print("Digite quantos gols o atacante possui:")
    val gol = snNumero.nextInt()

    when{
        gol == 0 -> println("Sem pontaria")
        gol in 1..2 -> println("Pontaria meia boca")
        gol in 3.. 5 -> println("Dá pro gasto")
        gol >5 -> println("Artilheiro")
        gol < 0 -> println("")
    }
}