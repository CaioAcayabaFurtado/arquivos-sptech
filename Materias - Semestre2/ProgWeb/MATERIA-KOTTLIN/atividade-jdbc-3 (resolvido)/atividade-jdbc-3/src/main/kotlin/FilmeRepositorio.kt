import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class FilmeRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "org.h2.Driver"
        dataSource.url = "jdbc:h2:mem:locadora"
        dataSource.username = "sa"
        dataSource.password = ""

        jdbcTemplate = JdbcTemplate(dataSource);
    }

    fun criarTabela() {
        jdbcTemplate.execute(
            """
        CREATE TABLE IF NOT EXISTS Filme(
        id INT PRIMARY KEY AUTO_INCREMENT,
        nome VARCHAR(50) NOT NULL,
        genero VARCHAR(25) NOT NULL,
        anoLancamento INT NOT NULL,
        duracao INT,
        alugado BOOLEAN
        )
        """.trimIndent()
        )
    }

    fun inserir(novoFilme: Filme): Boolean {
        if (novoFilme.nome.isBlank() || novoFilme.genero.isBlank() || novoFilme.anoLancamento <= 0) {
            return false
        }

        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado)
                VALUES(?, ?, ?, ?, ?)
            """,
            novoFilme.nome,
            novoFilme.genero,
            novoFilme.anoLancamento,
            novoFilme.duracao,
            novoFilme.alugado
        )

        return qtdLinhasAfetadas > 0;
    }

    fun listar(): List<Filme> {
        return jdbcTemplate.query(
            "SELECT * FROM Filme",
            BeanPropertyRowMapper(Filme::class.java)
        )
    }

    fun existePorId(id: Int): Boolean {
        val qtdEncontrado = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM Filme WHERE id = ?",
            Int::class.java,
            id
        )

        return qtdEncontrado > 0
    }

    fun buscarPorId(id: Int): Filme? {
        return try {
            jdbcTemplate.queryForObject(
                "SELECT * FROM Filme WHERE id = ?",
                BeanPropertyRowMapper(Filme::class.java),
                id
            )
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }


    fun deletarPorId(id: Int): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "DELETE FROM Filme WHERE id = ?",
            id
        )
        return qtdLinhasAfetadas > 0
    }

    fun atualizarPorId(id: Int, filme: Filme): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                UPDATE Filme SET
                nome = ?,
                genero = ?,
                anoLancamento = ?,
                duracao = ?,
                alugado = ?
                WHERE id = ?
            """,
            filme.nome,
            filme.genero,
            filme.anoLancamento,
            filme.duracao,
            filme.alugado,
            id
        )
        return qtdLinhasAfetadas > 0
    }

    fun alugadoPorId(id: Int): Boolean {
        return jdbcTemplate.queryForObject(
            "SELECT alugado FROM Filme WHERE id = ?",
            Boolean::class.java,
            id
        )
    }

    fun alugarPorId(id: Int): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                UPDATE Filme SET
                alugado = true
                WHERE id = ?
            """,
            id
        )
        return qtdLinhasAfetadas > 0
    }
}