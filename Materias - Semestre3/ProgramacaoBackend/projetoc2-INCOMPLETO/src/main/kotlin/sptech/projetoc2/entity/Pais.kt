package sptech.projetoc2.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Pais(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val codigo: Int? = null,
    val nome: String
){

}