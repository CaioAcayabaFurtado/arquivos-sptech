import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class ProdutoRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun configurar() {
        val datasource = BasicDataSource()
        datasource.driverClassName = "org.h2.Driver"
        datasource.url = "jdbc:h2:mem:estoque"
        datasource.username = "sa"
        datasource.password = ""

        jdbcTemplate = JdbcTemplate(datasource)
    }

    fun criarTabela(){
        jdbcTemplate.execute("""
            CREATE TABLE if not exists Produto(
                id int primary key auto_increment,
                nome varchar(255) not null,
                categoria varchar(255) not null,
                preco decimal(5,2) not null,
                fabricante varchar(255) not null
            )
        """.trimIndent())
    }

    //PARTE 3
    fun inserir(novoProduto:Produto): Boolean{
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
                insert into Produto (nome, categoria, preco, fabricante) values
                    (?, ?, ?, ?)
            """,
            novoProduto.nome,
            novoProduto.categoria,
            novoProduto.preco,
            novoProduto.fabricante
        )
        return qtdLinhasAfetadas>0
    }
    fun listar():List<Produto>{
        return jdbcTemplate.query(
            "select * from Produto",
            BeanPropertyRowMapper(Produto::class.java)
        )
    }
    fun existePorId(id:Int):Boolean{
        val qtdExistentes = jdbcTemplate.queryForObject(
            "select count(*) from Produto where id = ?",
            Int::class.java,
            id
        )
        return qtdExistentes > 0
    }
    fun buscarPorId(id:Int):Produto{
        val ProdutoEncontrado = jdbcTemplate.queryForObject(
            "select * from Produto where id =?",
            BeanPropertyRowMapper(Produto::class.java),
            id
        )
        return ProdutoEncontrado
    }
    fun atualizarPorId(id:Int, produtoParaAtualizar:Produto):Boolean{
        val qtdLinhasAfetadas = jdbcTemplate.update(
            """
               update Produto set
                nome = ?,
                categoria = ?,
                preco = ?,
                fabricante = ?
                    where id = ?
            """,
            produtoParaAtualizar.nome,
            produtoParaAtualizar.categoria,
            produtoParaAtualizar.preco,
            produtoParaAtualizar.fabricante,
            id
        )
        return qtdLinhasAfetadas>0
    }
    fun deletarPorId(id:Int):Boolean{
        val qtdLinhasAfetadas = jdbcTemplate.update(
            "delete from Produto where id = ?",
            id
        )
        return qtdLinhasAfetadas>0
    }
}