import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class MusicaRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "org.h2.Driver"
        // caso eu queira salvar um arquivo no projeto como se fosse um banco
//    datasource.url = "jdbc:h2:file:./festival"
        // jdbc:h2:file:c://usuarios//Aluno//Downloads//projetos//projetoXYZ//meubanco
        datasource.url = "jdbc:h2:mem:festival"
        datasource.username = "sa"
        datasource.password = ""

        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun criarTabela() {
        jdbcTemplate.execute("""
        CREATE TABLE IF NOT EXISTS Musica(
            id int PRIMARY KEY AUTO_INCREMENT,
            nome varchar(255) NOT NULL,
            banda varchar(255) NOT NULL,
            produtor varchar(255)
        )
    """.trimIndent())
    }

    fun inserir(novaMusica: Musica): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
               INSERT INTO Musica (nome, banda, produtor) 
               VALUES(?, ?, ?)
            """,
            novaMusica.nome,
            novaMusica.banda,
            novaMusica.produtor
        )

        return qtdLinhasAfetadas > 0
    }

    fun listar(): List<Musica> {
        return jdbcTemplate.query(
            "SELECT * FROM Musica",
            BeanPropertyRowMapper(Musica::class.java)
        )
    }

    fun existePorId(id: Int): Boolean {
        val qtdExistentes = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM Musica WHERE id = ?",
            Int::class.java,
            id
        )
        return qtdExistentes > 0
    }

    fun buscaPorId(id: Int): Musica {
        val musicaEncontrada = jdbcTemplate.queryForObject(
            "SELECT * FROM Musica WHERE id = ?",
            BeanPropertyRowMapper(Musica::class.java),
            id
        )
        return musicaEncontrada
    }

    fun deletaPorId(id: Int): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "DELETE FROM Musica WHERE id = ?",
            id
        )
        return qtdLinhasAfetadas > 0
    }

    fun atualizaPorId(id: Int, musicaAtualizada: Musica): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                UPDATE Musica SET
                nome = ?,
                banda = ?,
                produtor = ?
                WHERE id = ?
            """,
            musicaAtualizada.nome,
            musicaAtualizada.banda,
            musicaAtualizada.produtor,
            id
        )
        return qtdLinhasAfetadas > 0
    }
}