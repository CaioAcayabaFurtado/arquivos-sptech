package sptech.projetoc2.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

@Entity
data class Pugilista(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val codigo: Int? = null,

    @field:NotBlank
    @field:Size(min = 1, max = 20)
    val nome: String? = null,

    @field:NotBlank
    @ManyToOne
    val categoria: Categoria? = null,

    @field:NotBlank
    @ManyToOne
    val pais: Pais? = null,

    @field:NotNull
    @field:DecimalMin("50.0")
    val peso: Double? = null,

    @field:PositiveOrZero
    val vitoriasNocaute: Int = 0,

    @field:PositiveOrZero
    val vitoriasPontos: Int = 0,

    @field:PositiveOrZero
    val empates: Int = 0,

    @field:PositiveOrZero
    val derrotasNocaute: Int = 0,

    @field:PositiveOrZero
    val derrotasPontos: Int = 0,

    val aposentado: Boolean = false
)