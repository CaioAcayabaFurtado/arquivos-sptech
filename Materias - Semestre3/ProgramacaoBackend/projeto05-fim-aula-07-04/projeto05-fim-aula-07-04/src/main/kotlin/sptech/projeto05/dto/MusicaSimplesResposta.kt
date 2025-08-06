package sptech.projeto05.dto

/*
Aqui definimos um DTO usando uma técnica chamada
PROJECTIONS - DTOs baseados em interfaces
 */
interface MusicaSimplesResposta {
    val id: Int
    val nome: String
    val quantidadeReproducoes: Int
}