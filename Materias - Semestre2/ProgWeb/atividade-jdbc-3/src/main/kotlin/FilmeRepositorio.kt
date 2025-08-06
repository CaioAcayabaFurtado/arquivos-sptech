import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query

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
    fun criarTabela(){
        jdbcTemplate.execute(
            """
                create table filme(
                    id int primary key auto_increment,
                    nome varchar(45) not null,
                    genero varchar(45) not null,
                    anoLancamento int,
                    duracao char(5),
                    alugado boolean
                );
            """.trimIndent()
        )
    }
    fun inserir(novoFilme:Filme):Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update(
            """
                insert into filme (nome, genero, anoLancamento, duracao, alugado) values
                    (?,?,?,?,false);
            """.trimIndent(),
            novoFilme.nome,
            novoFilme.genero,
            novoFilme.anoLancamento,
            novoFilme.duracao,
        )
        return qtdLinhasInseridas>0
    }
    fun listar():List<Filme>{

        return jdbcTemplate.query(
            "select * from filme",
            BeanPropertyRowMapper(Filme::class.java),
            )
    }
    fun existePorId(id:Int): Boolean {
        val qtdExistentes = jdbcTemplate.queryForObject(
            "select count(*) from filme where id = ?",
            Int::class.java,
            id
        )
        return qtdExistentes > 0
    }
    fun buscarPorId(id:Int): Filme? {
        return jdbcTemplate.queryForObject(
            "select * from filme where id=?",
            BeanPropertyRowMapper(Filme::class.java),
            id
        )
    }
    fun atualizarPorId(id:Int, filmeParaAtualizar:Filme): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "update filme set nome=?, genero=?, anoLancamento=?, duracao=? where id=?",
            filmeParaAtualizar.nome,
            filmeParaAtualizar.genero,
            filmeParaAtualizar.anoLancamento,
            filmeParaAtualizar.duracao,
            id
        )
        return qtdLinhasAfetadas>0
    }

    fun deletarPorId(id:Int): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "delete from filme where id=?",
            id
        )
        return qtdLinhasAfetadas>0
    }

    fun alugadoPorId(id:Int): Boolean {
        val estaAlugado = jdbcTemplate.queryForObject(
            "select alugado from filme where id = ?",
            Boolean::class.java,
            id
        )
        return estaAlugado
    }
    fun alugarPorId(id:Int): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "update filme set alugado = true where id=?",
            id
        )
        return qtdLinhasAfetadas>0
    }
}