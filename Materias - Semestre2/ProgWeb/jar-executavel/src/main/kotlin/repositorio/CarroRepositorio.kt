package repositorio
import dominio.Carro
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class CarroRepositorio {
    lateinit var jdbcTemplate: JdbcTemplate
    fun configurar(){
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "org.h2.Driver"
        dataSource.url="jdbc:h2:mem:concessionaria"
        dataSource.username="sa"
        dataSource.password=""

        jdbcTemplate = JdbcTemplate(dataSource)
    }
    fun criarTabela(){
        jdbcTemplate.execute("""
            create table carro(
                id int primary key auto_increment,
                nome varchar(45) not null,
                vendido boolean
            )
        """.trimIndent())
    }
    fun inserir(novoCarro:Carro):Boolean{
        val qtdLinhasInseridas = jdbcTemplate.update("""
           insert into carro (nome, vendido) values
                (?, false);
           """,
            novoCarro.nome
        )
        return qtdLinhasInseridas > 0
    }
    fun buscarPorId(id:Int): Carro{
        return jdbcTemplate.queryForObject(
            "select * from carro where id=?",
            BeanPropertyRowMapper(Carro::class.java),
            id
        )
    }
    fun existePorId(id:Int):Boolean{
        val qtdExistentes = jdbcTemplate.queryForObject(
            "select count(*) from carro where id = ?",
            Int::class.java,
            id
        )
        return qtdExistentes > 0
    }
    fun venderPorId(id:Int):Boolean{
        val qtdAlterados = jdbcTemplate.update(
            "update carro set vendido=true where id=?",
            id
        )
        return qtdAlterados > 0
    }
}