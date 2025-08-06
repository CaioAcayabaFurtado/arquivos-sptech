import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class MusicaRepositorio{
    lateinit var jdbcTemplate: JdbcTemplate
    fun configurar(){
        val datasource = BasicDataSource()
        datasource.driverClassName = "org.h2.Driver"
        //caso queira salvar em um arquivo, como se fosse em um BD
        //datasource.url = "jdbc:h2:file:./festival"
        datasource.url = "jdbc:h2:mem:festival"
        datasource.username= "sa"
        datasource.password= ""

        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun criarTabela(){
        jdbcTemplate.execute("""
            CREATE TABLE if not exists Musica(
                id int primary key auto_increment,
                nome varchar(255) not null,
                banda varchar(255) not null,
                produtor varchar(255)
            )
        """.trimIndent())
    }

    fun inserir(novaMusica: Musica): Boolean {
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                insert into Musica (nome, banda, produtor) values
                    (?, ?, ?)
            """,
            novaMusica.nome,
            novaMusica.banda,
            novaMusica.produtor,
        )
        return qtdLinhasAfetadas > 0
    }

    fun listar():List<Musica>{
        return jdbcTemplate.query(
            "select * from Musica",
            BeanPropertyRowMapper(Musica::class.java)
        )
    }

    fun existePorId(id:Int):Boolean{
        val qtdExistentes = jdbcTemplate.queryForObject(
            "select count(*) from Musica where id = ?",
            Int::class.java,
            id
        )
        return qtdExistentes > 0
    }

    fun buscarPorId(id:Int):Musica{
        val musicaEncontrada = jdbcTemplate.queryForObject(
            "select * from Musica where id =?",
            BeanPropertyRowMapper(Musica::class.java),
            id
        )
        return musicaEncontrada
    }

    fun deletaPorId(id:Int):Boolean{
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "delete from musica where id = ?",
            id
        )
        return qtdLinhasAfetadas>0
    }
    
    fun atualizarPorId(id:Int, musicaAtualizada:Musica):Boolean{
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
               update Musica set
                nome = ?,
                banda = ?,
                produtor = ?
                    where id = ?
            """,
            musicaAtualizada.nome,
            musicaAtualizada.banda,
            musicaAtualizada.produtor,
            id
        )
        return qtdLinhasAfetadas>0
    }
}