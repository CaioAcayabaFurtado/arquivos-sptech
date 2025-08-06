import com.github.britooo.looca.api.core.Looca

// file - InfoSistema

fun main() {

    val looca = Looca()

    println(looca.sistema)
    println(looca.processador)
    println(looca.memoria)
    println(looca.temperatura)
    println(looca.rede.grupoDeInterfaces.interfaces)
    println(looca.grupoDeDiscos.tamanhoTotal)

}