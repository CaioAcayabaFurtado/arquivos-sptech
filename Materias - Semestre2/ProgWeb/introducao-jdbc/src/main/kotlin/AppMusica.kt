import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

fun main() {
    val datasource = BasicDataSource()
    datasource.driverClassName = "org.h2.Driver"
//    caso queira salvar em um arquivo, como se fosse em um BD
//    datasource.url = "jdbc:h2:file:./festival"
    datasource.url = "jdbc:h2:mem:festival"
    datasource.username= "sa"
    datasource.password= ""

    val jdbcTemplate = JdbcTemplate(datasource)

    //create table
    jdbcTemplate.execute("""
        CREATE TABLE Musica(
            id int primary key auto_increment,
            nome varchar(255) not null,
            banda varchar(255) not null,
            produtor varchar(255)
        )
    """.trimIndent())

    //insert
    val qtdInseridos = jdbcTemplate.update(
        "insert into Musica(nome,banda,produtor) values " +
            "('Still loving you', 'Scorpions', 'Records')"
    )
    println("linhas afetadas: $qtdInseridos")

    //select
    val listaMusicas:List<Musica> = jdbcTemplate.query(
        "select * from Musica",
        BeanPropertyRowMapper(Musica::class.java)
    )

    //exibir
    listaMusicas.forEach {
        println(
            """
            ---------------------------
            id: ${it.id}
            nome: ${it.nome}
            banda: ${it.banda}
            produtor: ${it.produtor}
        """.trimIndent()
        )
    }
    //filtro
    val listaFiltrada = listaMusicas.filter{musica-> musica.nome.length >3}

    val musicaId=1
    val existeMusica:Boolean = jdbcTemplate.queryForObject(
        "select count(*) from musica where id=?",
        Int::class.java,
        musicaId
    ) > 0

    if (existeMusica){
        val musicaEncontrada = jdbcTemplate.queryForObject(
            "select * from Musica where id =?",
            BeanPropertyRowMapper(Musica::class.java),
            musicaId
        )
        println("""
            ---- Buscar por ID ----
            id: ${musicaEncontrada.id}
            nome: ${musicaEncontrada.nome}
        """.trimIndent())
    } else {
        println("Musica nao encontrada")
    }








}
