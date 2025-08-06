import java.time.LocalDateTime
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.thread
import kotlin.system.exitProcess

//EXEMPLO SIMPLES
fun main2() {

    println("Início da execução: ${LocalDateTime.now()}")
    Timer().schedule(object : TimerTask(){
        override fun run() {
            println("Agora são: ${LocalDateTime.now()}")
            exitProcess(0)
        }
    }, 2000)
    println("Fim da execução: ${LocalDateTime.now()}")
}

//RECURSIVIDADE RECURSIVA

fun main() {
    mostrarHora()
}

fun mostrarHora(){
    Timer().schedule(object : TimerTask(){
        override fun run() {
            println("Agora são: ${LocalDateTime.now()}")
            mostrarHora()
        }
    }, 2000)
}

//EXEMPLO COM THREADSLEEP - Não é assincrono
fun main3() {
    while (true){
        println("Agora são: ${LocalDateTime.now()}")
        Thread.sleep(2000)
    }
}