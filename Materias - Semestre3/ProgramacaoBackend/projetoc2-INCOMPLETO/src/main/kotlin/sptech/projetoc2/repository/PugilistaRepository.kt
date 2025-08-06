package sptech.projetoc2.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import sptech.projetoc2.dtos.CartelPugilistaResponse
import sptech.projetoc2.entity.Pugilista

interface PugilistaRepository : JpaRepository<Pugilista, Int> {

    fun findByNomeIgnoreCaseContains(nome: String): List<Pugilista>
    fun findByAposentado(aposentado: Boolean): List<Pugilista>
    fun findByCategoriaNomeIgnoreCase(categoria: String): List<Pugilista>
    fun deleteByCodigo(codigo: Int)

    fun findByNomeIgnoreCaseContainsAndCategoriaNomeIgnoreCase (nome: String, categoria: String): List<Pugilista>

    @Transactional
    @Modifying
    @Query("UPDATE Pugilista p SET p.aposentado = true WHERE p.codigo = :codigo")
    fun aposentar(codigo: Int)

    @Query(value = """
        SELECT
            p.codigo,
            p.nome,
            (p.vitorias_nocaute + p.vitorias_pontos) AS vitorias,
            (p.derrotas_nocaute + p.derrotas_pontos) AS derrotas,
            p.empates,
            p.vitorias_nocaute
        FROM pugilista p
        WHERE p.codigo = ?1
    """,
        nativeQuery = true
    )
    fun getCartel(codigo: Int): CartelPugilistaResponse

    @Query(value = """
        SELECT *
        FROM pugilista
        ORDER BY vitorias_nocaute DESC
        LIMIT 3
    """, nativeQuery = true)
    fun findTop3ByOrderByVitoriasNocauteDesc(): List<Pugilista>

}
