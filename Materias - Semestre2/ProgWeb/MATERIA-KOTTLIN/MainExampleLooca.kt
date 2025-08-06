import com.github.britooo.looca.api.core.Looca

fun main() {

    // Criando uma Instância do Objeto Looca
    val looca = Looca();

    // Disponibilizando as interfaces de rede que meu computador está conectado
    val interfaces = looca.rede.grupoDeInterfaces.interfaces;

    // For para poder iterar sobre essas interfaces
    for (redeDaVez in interfaces){
        println(redeDaVez);
    }

    // Normalmente queremos a interface com nome = WLP, para isso vamos utilizar de um método que o Kotlin disponibiliza
    // Pegando nossa lista de interfaces atual, e vamos atrás do que queremos exatamente.
    val interfaceDeConexaoPrincipal = interfaces.filter { it.nome.lowercase().contains("wlp") };

    // Agora na variável interfaceDeConexaoPrincipal temos apenas a interface de rede que queremos.
    println("\n $interfaceDeConexaoPrincipal\n");

    // E assim como os objetos anteriores, podemos acessar os seus atributos
    // Sempre vai ser posição 0, pois é uma lista com apenas 1 instância de RedeInterface

    // DETALHE! COMO VEM EM BYTES HÁ A NECESSIDADE DE CASO VOCÊ MOSTRE PARA UM  USUÁRIO A DEVIDA CONVERSÃO
    val bytesEnviados = interfaceDeConexaoPrincipal[0].bytesEnviados;
    val bytesRecebidos = interfaceDeConexaoPrincipal[0].bytesEnviados;
    val pacotesEnviados = interfaceDeConexaoPrincipal[0].pacotesEnviados;
    val pacotesRecebidos = interfaceDeConexaoPrincipal[0].pacotesRecebidos;
    val bytesEnviadosConvertido = interfaceDeConexaoPrincipal[0].bytesEnviados / (1024 * 1024);
    val bytesRecebidosConvertido = interfaceDeConexaoPrincipal[0].bytesEnviados/ (1024 * 1024);

    println("""
        Bytes Enviados: $bytesEnviados,
        Bytes Recebidos: $bytesRecebidos,
        Pacotes Enviados: $pacotesEnviados,
        Pacotes Recebidos: $pacotesRecebidos,
        
        Bytes Enviados Convertido: $bytesEnviadosConvertido MB
        Bytes RecebidosConvertido: $bytesRecebidosConvertido MB
    """.trimIndent());

    // Agora para Janelas funciona de uma forma um pouco mais abrangente do que rede
    // Há dois tipos de Janelas
    // 1 - Vísiveis (Em primeiro Plano)
    // 2 - Não Vísiveis (Em segundo Plano)
    val janelas = looca.grupoDeJanelas;

    // Tipo 1
    val janelas1 = janelas.janelasVisiveis;
    // Tipo 2
    val janelas2 = janelas.janelas;

    // Printando todas as Janelas do tipo 1
    for (janelaDaVez in janelas1){
        println(janelaDaVez);
    }

    // Printando todas as Janelas do tipo 2
    for (janelaDaVez in janelas2) {
        println(janelaDaVez)
    }

    // E para pegarmos processos da nossa máquina
    val processos = looca.grupoDeProcessos.processos;

    // Printando todos os processos
    processos.forEach { processo -> 

    println("""
        
        PID: ${processo.pid}
        Nome: ${processo.nome}
        CPU: ${"%.2f".format(processo.usoCpu * 100)}%
        MEMORIA: ${"%.2f".format(processo.usoMemoria.toDouble() / (1024 * 1024))}MB
        MEMORIA VIRTUAL: ${"%.2f".format(processo.memoriaVirtualUtilizada.toDouble() / (1024 * 1024))}MB
    """.trimIndent()) }

}
