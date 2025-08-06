import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.BeanPropertyRowMapper

@DisplayName("Testes para a classe do repositório de produtos")
class ProdutoRepositorioTest {

    lateinit var produtoRepositorio: ProdutoRepositorio

    @BeforeEach
    fun setup() {

        produtoRepositorio = ProdutoRepositorio()

        val clazz = ProdutoRepositorio::class.java

        try {
            val method = clazz.getDeclaredMethod("configurar")
            method.invoke(produtoRepositorio)
        } catch (e: NoSuchMethodException) {
            fail("Método 'configurar' na 'ProdutoRepositorio' não encontrado... logo não foi atribuido ao 'jdbcTemplate'")
        }

        assertNotNull(produtoRepositorio.jdbcTemplate)

        produtoRepositorio.jdbcTemplate.execute("drop table if exists Produto")

        try {
            val clazz = ProdutoRepositorio::class.java
            val method = clazz.getDeclaredMethod("criarTabela")
            assertNotNull(method, "O método 'criarTabela' não foi encontrado na classe ProdutoRepositorio")

            method.invoke(produtoRepositorio)
        } catch (e: NoSuchMethodException) {
            fail("O método 'criarTabela' não foi encontrado na classe ProdutoRepositorio")
        }
    }

    @Nested
    @DisplayName("Cenários do método inserir")
    inner class Inserir {
        @Test
        fun `inserindo com sucesso`() {
            try {

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("inserir", Produto::class.java)

                val inserido = method.invoke(
                    produtoRepositorio,
                    ProdutoFixture.umProduto()
                )

                val produtos =
                    produtoRepositorio.jdbcTemplate.query(
                        "SELECT * FROM Produto",
                        BeanPropertyRowMapper(Produto::class.java)
                    )

                val produto = produtos[0]
                val clazzProduto = Produto::class.java

                val fieldId = clazzProduto.getDeclaredField("id")
                fieldId.isAccessible = true
                val fieldNome = clazzProduto.getDeclaredField("nome")
                fieldNome.isAccessible = true
                val fieldCategoria = clazzProduto.getDeclaredField("categoria")
                fieldCategoria.isAccessible = true
                val fieldPreco = clazzProduto.getDeclaredField("preco")
                fieldPreco.isAccessible = true
                val fieldFabricante = clazzProduto.getDeclaredField("fabricante")
                fieldFabricante.isAccessible = true

                assertEquals(true, inserido)
                assertEquals(1, produtos.size)
                assertEquals(1, fieldId.get(produto))
                assertEquals("Produto 1", fieldNome.get(produto))
                assertEquals("Categoria 1", fieldCategoria.get(produto))
                assertEquals(10.0, fieldPreco.get(produto))
                assertEquals("Fabricante 1", fieldFabricante.get(produto))

            } catch (e: NoSuchMethodException) {
                fail("O método 'inserir' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método listar")
    inner class Listar {
        @Test
        fun `listar com sucesso`() {
            try {

                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("listar")

                val produtos = method.invoke(produtoRepositorio) as List<Produto>

                val produto = produtos[0]
                val clazzProduto = Produto::class.java

                val fieldId = clazzProduto.getDeclaredField("id")
                fieldId.isAccessible = true
                val fieldNome = clazzProduto.getDeclaredField("nome")
                fieldNome.isAccessible = true
                val fieldCategoria = clazzProduto.getDeclaredField("categoria")
                fieldCategoria.isAccessible = true
                val fieldPreco = clazzProduto.getDeclaredField("preco")
                fieldPreco.isAccessible = true
                val fieldFabricante = clazzProduto.getDeclaredField("fabricante")
                fieldFabricante.isAccessible = true

                assertEquals(1, produtos.size)
                assertEquals(1, fieldId.get(produto))
                assertEquals("Produto 1", fieldNome.get(produto))
                assertEquals("Categoria 1", fieldCategoria.get(produto))
                assertEquals(10.0, fieldPreco.get(produto))
                assertEquals("Fabricante 1", fieldFabricante.get(produto))

            } catch (e: NoSuchMethodException) {
                fail("O método 'listar' não foi encontrado na classe ProdutoRepositorio.")
            }
        }

        @Test
        fun `listar sem produtos`() {
            try {

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("listar")

                val produtos = method.invoke(produtoRepositorio) as List<Produto>

                assertEquals(0, produtos.size)

            } catch (e: NoSuchMethodException) {
                fail("O método 'listar' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método existePorId")
    inner class ExistePorId {
        @Test
        fun `existe por id`() {
            try {
                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(produtoRepositorio, 1) as Boolean

                assertEquals(true, existe)
            } catch (e: NoSuchMethodException) {
                fail("O método 'existePorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }

        @Test
        fun `não existe por id`() {
            try {
                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(produtoRepositorio, 1) as Boolean

                assertEquals(false, existe)

            } catch (e: NoSuchMethodException) {
                fail("O método 'existePorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método buscarPorId")
    inner class BuscarPorId {
        @Test
        fun `busca por id`() {
            try {

                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("buscarPorId", Int::class.java)

                val produto = method.invoke(produtoRepositorio, 1) as Produto

                val clazzProduto = Produto::class.java

                val fieldId = clazzProduto.getDeclaredField("id")
                fieldId.isAccessible = true
                val fieldNome = clazzProduto.getDeclaredField("nome")
                fieldNome.isAccessible = true
                val fieldCategoria = clazzProduto.getDeclaredField("categoria")
                fieldCategoria.isAccessible = true
                val fieldPreco = clazzProduto.getDeclaredField("preco")
                fieldPreco.isAccessible = true
                val fieldFabricante = clazzProduto.getDeclaredField("fabricante")
                fieldFabricante.isAccessible = true

                assertEquals(1, fieldId.get(produto))
                assertEquals("Produto 1", fieldNome.get(produto))
                assertEquals("Categoria 1", fieldCategoria.get(produto))
                assertEquals(10.0, fieldPreco.get(produto))
                assertEquals("Fabricante 1", fieldFabricante.get(produto))

            } catch (e: NoSuchMethodException) {
                fail("O método 'buscarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }

        @Test
        fun `busca por id - produto não existe`() {
            try {
                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(produtoRepositorio, 2) as Boolean
                assertFalse(existe, "O produto não deveria existir, verifique se o método 'existePorId' está correto")
            } catch (e: NoSuchMethodException) {
                fail("O método 'buscarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método deletarPorId")
    inner class DeletarPorId {
        @Test
        fun `deleta por id`() {
            try {
                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("deletarPorId", Int::class.java)

                val deletado = method.invoke(produtoRepositorio, 1) as Boolean

                val produtos = produtoRepositorio.jdbcTemplate.query(
                    "SELECT * FROM Produto",
                    BeanPropertyRowMapper(Produto::class.java)
                )

                assertEquals(true, deletado)
                assertEquals(0, produtos.size)
            } catch (e: NoSuchMethodException) {
                fail("O método 'deletarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }

        @Test
        fun `deleta por id - produto não existe`() {
            try {
                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("deletarPorId", Int::class.java)

                val deletado = method.invoke(produtoRepositorio, 1) as Boolean

                val produtos = produtoRepositorio.jdbcTemplate.query(
                    "SELECT * FROM Produto",
                    BeanPropertyRowMapper(Produto::class.java)
                )

                assertEquals(
                    false,
                    deletado,
                    "O produto não deveria ter sido deletado (retornar true), verifique se o método 'deletarPorId' está correto"
                )
                assertEquals(0, produtos.size)
            } catch (e: NoSuchMethodException) {
                fail("O método 'deletarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método atualizarPorId")
    inner class AtualizarPorId {
        @Test
        fun `atualiza por id`() {
            try {

                produtoRepositorio.jdbcTemplate.update("INSERT INTO Produto (nome, categoria, preco, fabricante) VALUES ('Produto 1', 'Categoria 1', 10.0, 'Fabricante 1')")

                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("atualizarPorId", Int::class.java, Produto::class.java)

                val atualizado = method.invoke(
                    produtoRepositorio,
                    1,
                    ProdutoFixture.umProduto()
                ) as Boolean

                val produtos = produtoRepositorio.jdbcTemplate.query(
                    "SELECT * FROM Produto",
                    BeanPropertyRowMapper(Produto::class.java)
                )

                val produto = produtos[0]
                val clazzProduto = Produto::class.java

                val fieldId = clazzProduto.getDeclaredField("id")
                fieldId.isAccessible = true
                val fieldNome = clazzProduto.getDeclaredField("nome")
                fieldNome.isAccessible = true
                val fieldCategoria = clazzProduto.getDeclaredField("categoria")
                fieldCategoria.isAccessible = true
                val fieldPreco = clazzProduto.getDeclaredField("preco")
                fieldPreco.isAccessible = true
                val fieldFabricante = clazzProduto.getDeclaredField("fabricante")
                fieldFabricante.isAccessible = true

                assertEquals(true, atualizado)
                assertEquals(1, produtos.size)
                assertEquals(1, fieldId.get(produto))
                assertEquals("Produto 1", fieldNome.get(produto))
                assertEquals("Categoria 1", fieldCategoria.get(produto))
                assertEquals(10.0, fieldPreco.get(produto))
                assertEquals("Fabricante 1", fieldFabricante.get(produto))
            } catch (e: NoSuchMethodException) {
                fail("O método 'atualizarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }

        @Test
        fun `atualiza por id - produto não existe`() {
            try {
                val clazz = ProdutoRepositorio::class.java
                val method = clazz.getDeclaredMethod("atualizarPorId", Int::class.java, Produto::class.java)

                val atualizado = method.invoke(
                    produtoRepositorio,
                    1,
                    ProdutoFixture.umProduto()
                ) as Boolean

                val produtos = produtoRepositorio.jdbcTemplate.query(
                    "SELECT * FROM Produto",
                    BeanPropertyRowMapper(Produto::class.java)
                )

                assertEquals(
                    false,
                    atualizado,
                    "O produto não deveria ter sido atualizado (retornar true), verifique se o método 'atualizarPorId' está correto"
                )
                assertEquals(0, produtos.size)
            } catch (e: NoSuchMethodException) {
                fail("O método 'atualizarPorId' não foi encontrado na classe ProdutoRepositorio.")
            }
        }
    }
}