fun main() {

    val tarefas = mutableListOf<String>()

    while (true){
        println("Bem vindo(a)!")
        println("1 - Adicionar tarefa")
        println("2 - Remover tarefa")
        println("3 - Exibir lista")
        println("4 - Atualizar tarefa")
        println("5 - Exibir total de tarefas")
        println("6 - Sair")
        println("Digite o número de uma das ações:")


        val acao = readln().toInt()
        when (acao) {
            1 -> {
                println("Digite a tarefa:")
                val novaTarefa = readln()
                tarefas.add(novaTarefa)
            }
            2 -> {
                println("Digite a posição da tarefa que deseja remover:")
                val posicao = readln().toInt()
                if (tarefas.indices.contains(posicao) == true){
                    tarefas.removeAt(posicao)
                } else {
                    println("Posição inválida")
                }
            }
            3 -> {
                println("Lista atual: $tarefas")
            }
            4 -> {
                println("Suas tarefas: $tarefas")
                println("Digite a posição da tarefa que deseja atualizar:")
                var posicao = readln().toInt()
                posicao--
                if (tarefas.indices.contains(posicao) == true){
                    println("Digite a alteração:")
                    val nova = readln()
                    tarefas[posicao] = "$nova"
                } else {
                    println("Posição inválida")
                }
            }
            5 -> {
                println("Total de tarefas: ${tarefas.size}")
            }
            6 -> break
            else -> println("Opção inválida")
        }
        println("Ação concluída")
        Thread.sleep(3000)
    }
}