import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade da classe Pessoa")
class PessoaFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    {Pessoa::class.java.getDeclaredField("nome") },
                    "Atributo 'nome' não existe."
                )
            },
            {
                assertTrue(
                    {Pessoa::class.java.getDeclaredField("nome").type == String::class.java},
                    "Atributo 'nome' não é do tipo String")
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método setNome")
    inner class setNomeTests {
        @Test
        fun `setNome deve alterar o nome se o valor for maior que 2`() {
            try {
                val clazz = Pessoa::class.java
                val pessoa = clazz.getDeclaredConstructor().newInstance() as Pessoa

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(pessoa, "João")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(pessoa) as String

                assertEquals("João", nome, "O nome não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }

        @Test
        fun `setNome não deve alterar o nome se o valor for menor que 2`() {
            try {
                val clazz = Pessoa::class.java
                val pessoa = clazz.getDeclaredConstructor().newInstance() as Pessoa

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(pessoa, "J")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(pessoa) as String

                assertNotEquals("J", nome, "O nome não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método apresentar")
    inner class apresentarTests {
        @Test
        fun `apresentar deve retornar a mensagem correta se a pessoa for fiel`() {
            try {
                val clazz = Pessoa::class.java
                val pessoa = clazz.getDeclaredConstructor().newInstance() as Pessoa

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(pessoa, "João")

                val metodoApresentar = clazz.getDeclaredMethod("apresentar")

                assertEquals("Olá, meu nome é João.", metodoApresentar.invoke(pessoa), "A mensagem retornada não está correta")

            } catch (e: NoSuchMethodException) {
                fail("O método 'apresentar' não foi encontrado.")
            }
        }
    }
}