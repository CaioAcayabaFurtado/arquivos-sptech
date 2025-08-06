import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe Aventureiro")
class AventureiroFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    {Aventureiro::class.java.getDeclaredField("nome") },
                    "Atributo 'nome' não existe."
                )
            },
            {
                assertTrue(
                    {Aventureiro::class.java.getDeclaredField("nome").type == String::class.java},
                    "Atributo 'nome' não é do tipo String")
            },
            {
                assertDoesNotThrow(
                    {Aventureiro::class.java.getDeclaredField("energia") },
                    "Atributo 'energia' não existe."
                )
            },
            {
                assertTrue(
                    {Aventureiro::class.java.getDeclaredField("energia").type == Int::class.java},
                    "Atributo 'energia' não é do tipo Int")
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método setNome")
    inner class setNomeTests {
        @Test
        fun `setNome deve alterar o nome se o valor for maior ou igual que 3`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(aventureiro, "Aragorn")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(aventureiro) as String

                assertEquals("Aragorn", nome, "O nome não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }

        @Test
        fun `setNome não deve alterar o nome se o valor for menor que 3`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(aventureiro, "FF")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(aventureiro) as String

                assertEquals("", nome, "O nome não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setEnergia")
    inner class setEnergiaTests {
        @Test
        fun `setEnergia deve alterar a energia se o valor for maior que 0`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetEnergia = clazz.getDeclaredMethod("setEnergia", Int::class.java)

                metodoSetEnergia.invoke(aventureiro, 10)

                val energia = clazz.getDeclaredField("energia").apply { isAccessible = true }.get(aventureiro) as Int

                assertEquals(10, energia, "A energia não foi alterada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setEnergia' não foi encontrado.")
            }
        }

        @Test
        fun `setEnergia não deve alterar a energia se o valor for igual a 0`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetEnergia = clazz.getDeclaredMethod("setEnergia", Int::class.java)

                metodoSetEnergia.invoke(aventureiro, 0)

                val energia = clazz.getDeclaredField("energia").apply { isAccessible = true }.get(aventureiro) as Int

                assertEquals(0, energia, "A energia não deveria ter sido alterada")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setEnergia' não foi encontrado.")
            }
        }

        @Test
        fun `setEnergia não deve alterar a energia se o valor for menor que 0`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetEnergia = clazz.getDeclaredMethod("setEnergia", Int::class.java)

                metodoSetEnergia.invoke(aventureiro, -10)

                val energia = clazz.getDeclaredField("energia").apply { isAccessible = true }.get(aventureiro) as Int

                assertEquals(0, energia, "A energia não deveria ter sido alterada")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setEnergia' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método descansar")
    inner class descansarTests {
        @Test
        fun `descansar deve aumentar a energia em 10`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoDescansar = clazz.getDeclaredMethod("descansar")

                val energiaInicial = clazz.getDeclaredField("energia").apply { isAccessible = true }.get(aventureiro) as Int

                metodoDescansar.invoke(aventureiro)

                val energiaFinal = clazz.getDeclaredField("energia").apply { isAccessible = true }.get(aventureiro) as Int

                assertEquals(energiaInicial + 10, energiaFinal, "A energia não foi aumentada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'descansar' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método status")
    inner class statusTests {
        @Test
        fun `status deve retornar a mensagem correta`() {
            try {
                val clazz = Aventureiro::class.java
                val aventureiro = clazz.getDeclaredConstructor().newInstance() as Aventureiro

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)
                val metodoSetEnergia = clazz.getDeclaredMethod("setEnergia", Int::class.java)

                metodoSetNome.invoke(aventureiro, "Legolas")
                metodoSetEnergia.invoke(aventureiro, 10)

                val metodoStatus = clazz.getDeclaredMethod("status")

                val status = metodoStatus.invoke(aventureiro) as String

                assertEquals("Aventureiro Legolas tem 10 de energia.", status, "A mensagem não foi gerada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'status' não foi encontrado.")
            }
        }
    }
}