package pt1

fun main() {
    val tarefa = Tarefa()
    tarefa.nome="Dar banho no peixe"
    tarefa.pontuacao=8
    tarefa.status= StatusEnum.PENDENTE

    println(tarefa.status.nomeCompleto)
    println("Vou iniciar o banho...")

    tarefa.status= StatusEnum.EM_ANDAMENTO
    println(tarefa.status.nomeCompleto)

    if(tarefa.status.equals(StatusEnum.PENDENTE)){
        println("pt1.Tarefa pendente!")
    } else if(tarefa.status.equals(StatusEnum.EM_ANDAMENTO)){
        println("pt1.Tarefa em andamento!")
    } else{
        println("pt1.Tarefa concluida!")
    }
}