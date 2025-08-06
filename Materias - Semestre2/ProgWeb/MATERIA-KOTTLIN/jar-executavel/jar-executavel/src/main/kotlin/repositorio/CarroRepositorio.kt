package repositorio

import dominio.Carro
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class CarroRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "org.h2.Driver"
        dataSource.url = "jdbc:h2:mem:concessionaria"
        dataSource.username = "sa"
        dataSource.password = ""

        jdbcTemplate = JdbcTemplate(dataSource)
    }

    fun criarTabela() {
        jdbcTemplate.execute("""
            create table carro(
                id int primary key auto_increment,
                nome varchar(255) NOT NULL,
                vendido boolean
            )
        """.trimIndent())
    }

    fun inserir(novoCarro: Carro): Boolean {
        val qtdLinhasInseridas = jdbcTemplate.update(
            "INSERT INTO carro (nome, vendido) VALUES (?, false)",
                novoCarro.nome
            )

        return qtdLinhasInseridas > 0
    }

    fun buscarPorId(id: Int): Carro {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM carro WHERE id = ?",
            BeanPropertyRowMapper(Carro::class.java),
            id
        )
    }

    fun existePorId(id: Int): Boolean {
        val qtdExistentes = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM carro WHERE id = ?",
            Int::class.java,
            id
        )

        return qtdExistentes > 0
    }

    fun venderPorId(id: Int): Boolean {
        val qtdAlterados = jdbcTemplate.update(
            "UPDATE carro SET vendido = true WHERE id = ?",
            id
        )
        // caso o id não existe, a qtdAlterados é = 0
        return qtdAlterados > 0
    }

}