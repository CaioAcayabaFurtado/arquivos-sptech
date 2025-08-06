import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe Habilidade")
class HabilidadeFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    {Habilidade::class.java.getDeclaredField("nome") },
                    "Atributo 'nome' não existe."
                )
            },
            {
                assertTrue(
                    {Habilidade::class.java.getDeclaredField("nome").type == String::class.java},
                    "Atributo 'nome' não é do tipo String")
            },
            {
                assertDoesNotThrow(
                    {Habilidade::class.java.getDeclaredField("poder") },
                    "Atributo 'poder' não existe."
                )
            },
            {
                assertTrue(
                    {Habilidade::class.java.getDeclaredField("poder").type == Int::class.java},
                    "Atributo 'poder' não é do tipo Int")
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método setNome")
    inner class setNomeTests {
        @Test
        fun `setNome deve alterar o nome se o valor for maior ou igual que 2`() {
            try {
                val clazz = Habilidade::class.java
                val habilidade = clazz.getDeclaredConstructor().newInstance() as Habilidade

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(habilidade, "Fogo")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(habilidade) as String

                assertEquals("Fogo", nome, "O nome não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }

        @Test
        fun `setNome não deve alterar o nome se o valor for menor que 2`() {
            try {
                val clazz = Habilidade::class.java
                val habilidade = clazz.getDeclaredConstructor().newInstance() as Habilidade

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(habilidade, "F")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(habilidade) as String

                assertNotEquals("F", nome, "O nome não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }
    }
}