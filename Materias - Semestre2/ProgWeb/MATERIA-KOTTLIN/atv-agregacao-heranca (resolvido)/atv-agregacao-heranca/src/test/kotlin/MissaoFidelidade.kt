import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe Missao")
class MissaoFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { Missao::class.java.getDeclaredField("nome") },
                    "Atributo 'nome' não existe."
                )
            },
            {
                assertTrue(
                    { Missao::class.java.getDeclaredField("nome").type == String::class.java },
                    "Atributo 'nome' não é do tipo String"
                )
            },
            {
                assertDoesNotThrow(
                    { Missao::class.java.getDeclaredField("descricao") },
                    "Atributo 'descricao' não existe."
                )
            },
            {
                assertTrue(
                    { Missao::class.java.getDeclaredField("descricao").type == String::class.java },
                    "Atributo 'descricao' não é do tipo String"
                )
            },
            {
                assertDoesNotThrow(
                    { Missao::class.java.getDeclaredField("nivelDificuldade") },
                    "Atributo 'nivelDificuldade' não existe."
                )
            },
            {
                assertTrue(
                    { Missao::class.java.getDeclaredField("nivelDificuldade").type == Int::class.java },
                    "Atributo 'nivelDificuldade' não é do tipo Int"
                )
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método setNome")
    inner class setNomeTests {
        @Test
        fun `setNome deve alterar o nome se o valor for maior ou igual que 3`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(missao, "Matar o dragão")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(missao) as String

                assertEquals("Matar o dragão", nome, "O nome não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }

        @Test
        fun `setNome não deve alterar o nome se o valor for menor que 3`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(missao, "MM")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(missao) as String

                assertNotEquals("MM", nome, "O nome não deveria ter sido alterado")
            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setDescricao")
    inner class setDescricaoTests {
        @Test
        fun `setDescricao deve alterar a descricao se o valor for maior ou igual que 10`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetDescricao = clazz.getDeclaredMethod("setDescricao", String::class.java)

                metodoSetDescricao.invoke(missao, "Matar o dragão que está aterrorizando a vila")

                val descricao = clazz.getDeclaredField("descricao").apply { isAccessible = true }.get(missao) as String

                assertEquals(
                    "Matar o dragão que está aterrorizando a vila",
                    descricao,
                    "A descricao não foi alterada corretamente"
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setDescricao' não foi encontrado.")
            }
        }

        @Test
        fun `setDescricao deve alterar a descricao se o valor for exatamente 10`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetDescricao = clazz.getDeclaredMethod("setDescricao", String::class.java)

                metodoSetDescricao.invoke(missao, "Matar o dr")

                val descricao = clazz.getDeclaredField("descricao").apply { isAccessible = true }.get(missao) as String

                assertEquals(
                    "Matar o dr",
                    descricao,
                    "A descricao não foi alterada corretamente"
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setDescricao' não foi encontrado.")
            }
        }

        @Test
        fun `setDescricao não deve alterar a descricao se o valor for menor que 10`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetDescricao = clazz.getDeclaredMethod("setDescricao", String::class.java)

                metodoSetDescricao.invoke(missao, "Matar o d")

                val descricao = clazz.getDeclaredField("descricao").apply { isAccessible = true }.get(missao) as String

                assertNotEquals("Matar o d", descricao, "A descricao não deveria ter sido alterada")
            } catch (e: NoSuchMethodException) {
                fail("O método 'setDescricao' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setNivelDificuldade")
    inner class setNivelDificuldadeTests {
        @Test
        fun `setNivelDificuldade deve alterar o nivelDificuldade se o valor for maior que 0`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetNivelDificuldade = clazz.getDeclaredMethod("setNivelDificuldade", Int::class.java)

                metodoSetNivelDificuldade.invoke(missao, 10)

                val nivelDificuldade =
                    clazz.getDeclaredField("nivelDificuldade").apply { isAccessible = true }.get(missao) as Int

                assertEquals(10, nivelDificuldade, "O nivelDificuldade não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelDificuldade' não foi encontrado.")
            }
        }

        @Test
        fun `setNivelDificuldade não deve alterar o nivelDificuldade se o valor for igual a 0`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetNivelDificuldade = clazz.getDeclaredMethod("setNivelDificuldade", Int::class.java)

                metodoSetNivelDificuldade.invoke(missao, 0)

                val nivelDificuldade =
                    clazz.getDeclaredField("nivelDificuldade").apply { isAccessible = true }.get(missao) as Int

                assertEquals(0, nivelDificuldade, "O nivelDificuldade não deveria ter sido alterado")
            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelDificuldade' não foi encontrado.")
            }
        }

        @Test
        fun `setNivelDificuldade não deve alterar o nivelDificuldade se o valor for menor que 0`() {
            try {
                val clazz = Missao::class.java
                val missao = clazz.getDeclaredConstructor().newInstance() as Missao

                val metodoSetNivelDificuldade = clazz.getDeclaredMethod("setNivelDificuldade", Int::class.java)

                metodoSetNivelDificuldade.invoke(missao, -10)

                val nivelDificuldade =
                    clazz.getDeclaredField("nivelDificuldade").apply { isAccessible = true }.get(missao) as Int

                assertEquals(0, nivelDificuldade, "O nivelDificuldade não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelDificuldade' não foi encontrado.")
            }
        }
    }
}