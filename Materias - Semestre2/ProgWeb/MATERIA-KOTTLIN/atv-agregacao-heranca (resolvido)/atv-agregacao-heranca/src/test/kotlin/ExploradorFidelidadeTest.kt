import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe Explorador")
class ExploradorFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { Explorador::class.java.getDeclaredField("nivelExperiencia") },
                    "Atributo 'nivelExperiencia' não existe."
                )
            },
            {
                assertTrue(
                    { Explorador::class.java.getDeclaredField("nivelExperiencia").type == Int::class.java },
                    "Atributo 'nivelExperiencia' não é do tipo Int"
                )
            },
            {
                assertDoesNotThrow(
                    { Explorador::class.java.getDeclaredField("missoes") },
                    "Atributo 'missoes' não existe."
                )
            },
            {
                assertTrue(
                    { Explorador::class.java.getDeclaredField("missoes").type == MutableList::class.java },
                    "Atributo 'missoes' não é do tipo MutableList ou não recebe mutableListOf<Missao> como valor padrão"
                )
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método inscreverEmMissao")
    inner class inscreverEmMissaoTests {
        @Test
        fun `inscreverEmMissao deve adicionar uma missão à lista de missões`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoInscreverEmMissao = clazz.getDeclaredMethod("inscreverEmMissao", Missao::class.java)
                val missao = MissaoFixture.umaMissao()

                metodoInscreverEmMissao.invoke(explorador, missao)

                val missoes = clazz.getDeclaredField("missoes").apply { isAccessible = true }.get(explorador) as MutableList<Missao>

                assertTrue(missoes.contains(missao), "A missão não foi adicionada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'inscreverEmMissao' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método listarMissoes")
    inner class listarMissoesTests {
        @Test
        fun `listarMissoes deve retornar a lista de missões`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoListarMissoes = clazz.getDeclaredMethod("listarMissoes")

                val missoes = MissaoFixture.missoes()

                for (missao in missoes) {
                    val metodoInscreverEmMissao = clazz.getDeclaredMethod("inscreverEmMissao", Missao::class.java)
                    metodoInscreverEmMissao.invoke(explorador, missao)
                }

                val missoesRetornadas = metodoListarMissoes.invoke(explorador) as List<Missao>

                assertEquals(missoes, missoesRetornadas, "A lista de missões não foi retornada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'listarMissoes' não foi encontrado.")
            }
        }

        @Test
        fun `listarMissoes deve retornar uma lista vazia se não houver missões`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoListarMissoes = clazz.getDeclaredMethod("listarMissoes")

                val missoes = mutableListOf<Missao>()

                val missoesRetornadas = metodoListarMissoes.invoke(explorador) as List<Missao>

                assertEquals(missoes, missoesRetornadas, "A lista de missões não foi retornada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'listarMissoes' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método abandonarMissao")
    inner class abandonarMissaoTests {
        @Test
        fun `abandonarMissao deve remover uma missão da lista de missões`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoInscreverEmMissao = clazz.getDeclaredMethod("inscreverEmMissao", Missao::class.java)
                val metodoAbandonarMissao = clazz.getDeclaredMethod("abandonarMissao", String::class.java)
                val missao = MissaoFixture.umaMissao()

                metodoInscreverEmMissao.invoke(explorador, missao)

                metodoAbandonarMissao.invoke(explorador, "Missão Exploratória")

                val missoes = clazz.getDeclaredField("missoes").apply { isAccessible = true }.get(explorador) as MutableList<Missao>

                assertFalse(missoes.contains(missao), "A missão não foi removida corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'abandonarMissao' não foi encontrado.")
            }
        }

        @Test
        fun `abandonarMissao não deve remover uma missão da lista de missões se o nome da missão não existir`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoInscreverEmMissao = clazz.getDeclaredMethod("inscreverEmMissao", Missao::class.java)
                val metodoAbandonarMissao = clazz.getDeclaredMethod("abandonarMissao", String::class.java)
                val missao = MissaoFixture.umaMissao()

                metodoInscreverEmMissao.invoke(explorador, missao)
                metodoAbandonarMissao.invoke(explorador, "Missão que não existe")

                val missoes = clazz.getDeclaredField("missoes").apply { isAccessible = true }.get(explorador) as MutableList<Missao>

                assertTrue(missoes.contains(missao), "A missão não deveria ter sido removida")

            } catch (e: NoSuchMethodException) {
                fail("O método 'abandonarMissao' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método ganharExperiencia")
    inner class ganharExperienciaTests {
        @Test
        fun `ganharExperiencia deve adicionar a experiência ao nível de experiência do explorador`() {
            try {
                val clazz = Explorador::class.java
                val explorador = clazz.getDeclaredConstructor().newInstance() as Explorador

                val metodoGanharExperiencia = clazz.getDeclaredMethod("ganharExperiencia", Int::class.java)
                val experiencia = 10

                metodoGanharExperiencia.invoke(explorador, experiencia)

                val nivelExperiencia = clazz.getDeclaredField("nivelExperiencia").apply { isAccessible = true }.get(explorador) as Int

                assertEquals(experiencia, nivelExperiencia, "A experiência não foi adicionada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'ganharExperiencia' não foi encontrado.")
            }
        }
    }
}