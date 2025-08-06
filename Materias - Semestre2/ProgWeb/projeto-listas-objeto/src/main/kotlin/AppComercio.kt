import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread

fun main() {
    val bancoDeDados = mutableListOf<Produto>()
    println("Bem vindo ao gerenciamento de produtos!")
    while (true){
        println("""
            ----Produtos----
            1 - Adicionar produto
            2 - Buscar protuto por indice (começa de 0)
            3 - Remover produto por indice (começa de 0)
            4 - Verificar validade por indice (começa de 0)
            5 - Sair
        """.trimIndent())
        println("Digite a opção desejada:")
        val opcao = readln().toInt()
        when (opcao){
            1 -> {
                val novoProduto = Produto()
                println("Digite o nome do produto:")
                novoProduto.nome = readln()
                println("Digite o preço do produto:")
                novoProduto.preco = readln().toDouble()
                    //println("Digite o ano do vencimento:")
                    //val ano = readln().toInt()
                    //println("Digite o mes do vencimento:")
                    //val mes = readln().toInt()
                    //println("Digite o dia do vencimento:")
                    //val dia = readln().toInt()
                    //novoProduto.validade= LocalDate.of(ano,mes,dia)
                println("Digite a data de validade dd/MM/yyyy")
                val dataTexto = readln()// 09/09/2024 - exemplo
                val formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                // parse (texto que chegou, formatador indicando o jeito que veio)
                val dataFormatada = LocalDate.parse(dataTexto,formatador)
                bancoDeDados.add(novoProduto)
            }
            2 ->{
                println("Digite o produto que deseja listar:")
                val i = readln().toInt()
                if (i >= 0 && i < bancoDeDados.size) {
                    val produtoEncontrado = bancoDeDados.get(i)
                    val formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    println("O produto ${produtoEncontrado.nome} custa R$${produtoEncontrado.preco}, tem validade ${produtoEncontrado.validade.format(formatador)}.")
                } else {
                    println("Produto não encontrado!")
                }
            }
            3 ->{
                println("Digite o produto que deseja remover:")
                val i = readln().toInt()
                if (i >= 0 && i < bancoDeDados.size) {
                    bancoDeDados.removeAt(i)
                    println("O produto foi removido.")
                } else {
                    println("Produto não encontrado!")
                }
            }
            4 ->{
                println("Digite o produto que deseja listar:")
                val i = readln().toInt()
                if (i >= 0 && i < bancoDeDados.size) {
                    val produtoEncontrado = bancoDeDados.get(i)
                    println(produtoEncontrado.descrever())
                }else {
                    println("Produto não encontrado!")
                }
            }
            5 -> break
            else -> println("Comando inválido!")
        }
    }
    println("Finalizando...")
}