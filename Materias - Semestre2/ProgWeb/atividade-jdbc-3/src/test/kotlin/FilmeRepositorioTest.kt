import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.jdbc.core.BeanPropertyRowMapper

@DisplayName("Testes para a classe do repositório de filmes")
class FilmeRepositorioTest {

    private lateinit var filmeRepositorio: FilmeRepositorio;

    @BeforeEach
    fun setup() {

        filmeRepositorio = FilmeRepositorio();

        val clazz = FilmeRepositorio::class.java;

        try {
            val method = clazz.getDeclaredMethod("configurar");
            method.invoke(filmeRepositorio);
        } catch (e: NoSuchMethodException) {
            fail("Método 'configurar' na 'FilmeRepositorio' não encontrado... logo não foi atribuido ao 'jdbcTemplate'");
        }

        assertNotNull(filmeRepositorio.jdbcTemplate);

        filmeRepositorio.jdbcTemplate.execute("drop table if exists Filme");

        try {
            val method = clazz.getDeclaredMethod("criarTabela");
            assertNotNull(method, "O método 'criarTabela' não foi encontrado na classe 'FilmeRepositorio'");
            method.invoke(filmeRepositorio);
        } catch (e: NoSuchMethodException) {
            fail("Método 'criarTabela' na 'FilmeRepositorio' não encontrado... logo não foi criada a tabela 'Filme'");
        }
    }

    @Nested
    @DisplayName("Cenários do método inserir")
    inner class Inserir {
        @Test
        fun `inserindo com sucesso`() {
            try {
                val clazz = FilmeRepositorio::class.java;
                val method = clazz.getDeclaredMethod("inserir", Filme::class.java);

                val inserido = method.invoke(
                    filmeRepositorio,
                    FilmeFixture.umFilme()
                );

                val filmes = filmeRepositorio.jdbcTemplate.query(
                    "SELECT * FROM Filme",
                    BeanPropertyRowMapper(Filme::class.java)
                )

                val filme = filmes[0]
                val clazzFilme = Filme::class.java

                val fieldId = clazzFilme.getDeclaredField("id").apply {
                    isAccessible = true
                }

                val fieldNome = clazzFilme.getDeclaredField("nome").apply {
                    isAccessible = true
                }

                val fieldGenero = clazzFilme.getDeclaredField("genero").apply {
                    isAccessible = true
                }

                val fieldAnoLancamento = clazzFilme.getDeclaredField("anoLancamento").apply {
                    isAccessible = true
                }

                val fieldDuracao = clazzFilme.getDeclaredField("duracao").apply {
                    isAccessible = true
                }

                val fieldAlugado = clazzFilme.getDeclaredField("alugado").apply {
                    isAccessible = true
                }

                assertEquals(true, inserido, "O filme deve ser inserido com sucesso")
                assertEquals(1, filmes.size, "Deve haver um filme na lista")
                assertEquals(1, fieldId.get(filme), "O id do filme deve ser 1")
                assertEquals("Shrek", fieldNome.get(filme), "O nome do filme deve ser preenchido")
                assertEquals("Comédia", fieldGenero.get(filme), "O gênero do filme deve ser preenchido")
                assertEquals(2001, fieldAnoLancamento.get(filme), "O ano de lançamento do filme deve ser preenchido")
                assertEquals(90, fieldDuracao.get(filme), "A duração do filme deve ser preenchida")
                assertEquals(false, fieldAlugado.get(filme))

            } catch (e: NoSuchMethodException) {
                fail("Método 'inserir' na 'FilmeRepositorio' não encontrado");
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método listar")
    inner class Listar {
        @Test
        fun `método listar`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) " +
                            "VALUES('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java;
                val method = clazz.getDeclaredMethod("listar")

                val filmes = method.invoke(filmeRepositorio) as List<Filme>

                val filme = filmes[0]
                val clazzFilme = Filme::class.java

                val fieldId = clazzFilme.getDeclaredField("id").apply {
                    isAccessible = true
                }

                val fieldNome = clazzFilme.getDeclaredField("nome").apply {
                    isAccessible = true
                }

                val fieldGenero = clazzFilme.getDeclaredField("genero").apply {
                    isAccessible = true
                }

                val fieldAnoLancamento = clazzFilme.getDeclaredField("anoLancamento").apply {
                    isAccessible = true
                }

                val fieldDuracao = clazzFilme.getDeclaredField("duracao").apply {
                    isAccessible = true
                }

                val fieldAlugado = clazzFilme.getDeclaredField("alugado").apply {
                    isAccessible = true
                }

                assertEquals(1, filmes.size, "Deve haver um filme na lista")
                assertEquals(1, fieldId.get(filme))
                assertEquals("Shrek", fieldNome.get(filme))
                assertEquals("Comédia", fieldGenero.get(filme))
                assertEquals(2001, fieldAnoLancamento.get(filme))
                assertEquals(90, fieldDuracao.get(filme))
                assertEquals(false, fieldAlugado.get(filme))
            } catch (e: NoSuchMethodException) {
                fail("Método 'listar' na 'FilmeRepositorio' não encontrado");
            }
        }

        @Test
        fun `método listar - sem músicas`() {
            try {
                val clazz = FilmeRepositorio::class.java;
                val method = clazz.getDeclaredMethod("listar")

                val filmes = method.invoke(filmeRepositorio) as List<Filme>

                assertEquals(0, filmes.size, "Não deve haver filmes na lista")
            } catch (e: NoSuchMethodException) {
                fail("Método 'listar' na 'FilmeRepositorio' não encontrado");
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método existePorId")
    inner class ExistePorId {
        @Test
        fun `existePorId - Existe Filme`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(filmeRepositorio, 1) as Boolean
                assertTrue(existe, "O produto deveria existir, verifique se o método 'existePorId' está correto")
            } catch (e: NoSuchMethodException) {
                fail("Método 'existePorId' na 'FilmeRepositorio' não encontrado");
            }
        }

        @Test
        fun `existePorId - Não Existe Filme`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(filmeRepositorio, 2) as Boolean
                assertFalse(existe, "O produto não deveria existir, verifique se o método 'existePorId' está correto")
            } catch (e: NoSuchMethodException) {
                fail("Método 'existePorId' na 'FilmeRepositorio' não encontrado");
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método buscarPorId")
    inner class BuscaPorId {
        @Test
        fun `buscarPorId - Existe Filme`() {
            try {

                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("buscarPorId", Int::class.java)

                val filme = method.invoke(filmeRepositorio, 1) as Filme?

                val clazzFilme = Filme::class.java;

                val fieldId = clazzFilme.getDeclaredField("id").apply {
                    isAccessible = true
                }

                val fieldNome = clazzFilme.getDeclaredField("nome").apply {
                    isAccessible = true
                }

                val fieldGenero = clazzFilme.getDeclaredField("genero").apply {
                    isAccessible = true
                }

                val fieldAnoLancamento = clazzFilme.getDeclaredField("anoLancamento").apply {
                    isAccessible = true
                }

                val fieldDuracao = clazzFilme.getDeclaredField("duracao").apply {
                    isAccessible = true
                }

                val fieldAlugado = clazzFilme.getDeclaredField("alugado").apply {
                    isAccessible = true
                }

                assertNotNull(filme, "Deve retornar um filme quando encontrado.")
                assertEquals(1, fieldId.get(filme))
                assertEquals("Shrek", fieldNome.get(filme))
                assertEquals("Comédia", fieldGenero.get(filme))
                assertEquals(2001, fieldAnoLancamento.get(filme))
                assertEquals(90, fieldDuracao.get(filme))
                assertEquals(false, fieldAlugado.get(filme))
            } catch (e: NoSuchMethodException) {
                fail("Método 'buscarPorId' na 'FilmeRepositorio' não encontrado");
            }
        }

        @Test
        fun `buscarPorId - Não Existe Filme`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("existePorId", Int::class.java)

                val existe = method.invoke(filmeRepositorio, 2) as Boolean
                assertFalse(existe, "O produto não deveria existir, verifique se o método 'existePorId' está correto")
            } catch (e: NoSuchMethodException) {
                fail("Método 'existePorId' na 'FilmeRepositorio' não encontrado");
            }

        }
    }

    @Nested
    @DisplayName("Cenários do método deletarPorId")
    inner class DeletaPorId {
        @Test
        fun `deletarPorId - Existe Filme para Deletar`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("deletarPorId", Int::class.java)

                val deletado = method.invoke(filmeRepositorio, 1) as Boolean

                val filmes =
                    filmeRepositorio.jdbcTemplate.query(
                        "SELECT * FROM Filme",
                        BeanPropertyRowMapper(Filme::class.java)
                    )

                assertEquals(true, deletado)
                assertEquals(0, filmes.size)
            } catch (e: NoSuchMethodException) {
                fail("Método 'deletarPorId' na 'FilmeRepositorio' não encontrado")
            }
        }

        @Test
        fun `deletarPorId - Não Existe Filme para Deletar`() {

            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("deletarPorId", Int::class.java)

                val deletado = method.invoke(filmeRepositorio, 2) as Boolean

                val filmes =
                    filmeRepositorio.jdbcTemplate.query(
                        "SELECT * FROM Filme",
                        BeanPropertyRowMapper(Filme::class.java)
                    )

                assertEquals(false, deletado)
                assertEquals(1, filmes.size)
            } catch (e: NoSuchMethodException) {
                fail("Método 'deletarPorId' na 'FilmeRepositorio' não encontrado")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método alugadoPorId")
    inner class AlugadoPorId {
        @Test
        fun `alugadoPorId - O filme NÃO está alugado`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("alugadoPorId", Int::class.java)

                val disponivel = method.invoke(filmeRepositorio, 1) as Boolean

                assertFalse(disponivel)
            } catch (e: NoSuchMethodException) {
                fail("Método 'alugadoPorId' na 'FilmeRepositorio' não encontrado")
            }
        }

        @Test
        fun `alugadoPorId - O filme está alugado`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, true)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("alugadoPorId", Int::class.java)

                val disponivel = method.invoke(filmeRepositorio, 1) as Boolean

                assertTrue(disponivel)
            } catch (e: NoSuchMethodException) {
                fail("Método 'alugadoPorId' na 'FilmeRepositorio' não encontrado")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método alugarPorId")
    inner class AlugarPorId {
        @Test
        fun `alugarPorId - Existe Filme para Atualizar`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("alugarPorId", Int::class.java)

                val atualizado = method.invoke(filmeRepositorio, 1) as Boolean

                val filmes =
                    filmeRepositorio.jdbcTemplate.query(
                        "SELECT * FROM Filme",
                        BeanPropertyRowMapper(Filme::class.java)
                    )

                val filme = filmes[0]
                val clazzFilme = Filme::class.java

                val fieldId = clazzFilme.getDeclaredField("id").apply {
                    isAccessible = true
                }

                val fieldNome = clazzFilme.getDeclaredField("nome").apply {
                    isAccessible = true
                }

                val fieldGenero = clazzFilme.getDeclaredField("genero").apply {
                    isAccessible = true
                }

                val fieldAnoLancamento = clazzFilme.getDeclaredField("anoLancamento").apply {
                    isAccessible = true
                }

                val fieldDuracao = clazzFilme.getDeclaredField("duracao").apply {
                    isAccessible = true
                }

                val fieldAlugado = clazzFilme.getDeclaredField("alugado").apply {
                    isAccessible = true
                }

                assertEquals(true, atualizado)
                assertEquals(1, filmes.size)
                assertEquals(1, fieldId.get(filme))
                assertEquals("Shrek", fieldNome.get(filme))
                assertEquals("Comédia", fieldGenero.get(filme))
                assertEquals(2001, fieldAnoLancamento.get(filme))
                assertEquals(90, fieldDuracao.get(filme))
                assertEquals(true, fieldAlugado.get(filme))
            } catch (e: NoSuchMethodException) {
                fail("Método 'alugarPorId' na 'FilmeRepositorio' não encontrado")
            }
        }

        @Test
        fun `alugarPorId - Não Existe Filme para Atualizar`() {
            try {
                filmeRepositorio.jdbcTemplate.update(
                    "INSERT INTO Filme (nome, genero, anoLancamento, duracao, alugado) VALUES " +
                            "('Shrek', 'Comédia', 2001, 90, false)"
                )

                val clazz = FilmeRepositorio::class.java
                val method = clazz.getDeclaredMethod("alugarPorId", Int::class.java)

                val atualizado = method.invoke(filmeRepositorio, 2) as Boolean

                val filmes =
                    filmeRepositorio.jdbcTemplate.query(
                        "SELECT * FROM Filme",
                        BeanPropertyRowMapper(Filme::class.java)
                    )

                val filme = filmes[0]
                val clazzFilme = Filme::class.java

                val fieldId = clazzFilme.getDeclaredField("id").apply {
                    isAccessible = true
                }

                val fieldNome = clazzFilme.getDeclaredField("nome").apply {
                    isAccessible = true
                }

                val fieldGenero = clazzFilme.getDeclaredField("genero").apply {
                    isAccessible = true
                }

                val fieldAnoLancamento = clazzFilme.getDeclaredField("anoLancamento").apply {
                    isAccessible = true
                }

                val fieldDuracao = clazzFilme.getDeclaredField("duracao").apply {
                    isAccessible = true
                }

                val fieldAlugado = clazzFilme.getDeclaredField("alugado").apply {
                    isAccessible = true
                }

                assertEquals(false, atualizado)
                assertEquals(1, filmes.size)
                assertEquals(1, fieldId.get(filme))
                assertEquals("Shrek", fieldNome.get(filme))
                assertEquals("Comédia", fieldGenero.get(filme))
                assertEquals(2001, fieldAnoLancamento.get(filme))
                assertEquals(90, fieldDuracao.get(filme))
                assertEquals(false, fieldAlugado.get(filme))
            } catch (e: NoSuchMethodException) {
                fail("Método 'alugarPorId' na 'FilmeRepositorio' não encontrado")
            }
        }
    }
}