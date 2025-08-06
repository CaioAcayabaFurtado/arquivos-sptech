import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe HeroiTecnologico")
class HeroiSobrenaturalFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { HeroiSobrenatural::class.java.getDeclaredField("poderSecundario") },
                    "Atributo 'poderSecundario' não existe."
                )
            },
            {
                assertTrue(
                    { HeroiSobrenatural::class.java.getDeclaredField("poderSecundario").type == String::class.java },
                    "Atributo 'poderSecundario' não é do tipo String"
                )
            }
        )
    }

    @Test
    fun `Verificar se métodos foram implementados corretamente`() {
        assertAll(
            "Métodos",
            {
                assertDoesNotThrow(
                    { HeroiSobrenatural::class.java.getDeclaredMethod("usarPoder") },
                    "Método 'usarPoder' não foi encontrado."
                )
            },
            {
                assertDoesNotThrow(
                    { HeroiSobrenatural::class.java.getDeclaredMethod("descrever") },
                    "Método 'descrever' não foi encontrado."
                )
            },
            {
                assertDoesNotThrow(
                    { HeroiSobrenatural::class.java.getDeclaredMethod("setPoderSecundario", String::class.java) },
                    "Método 'setPoderSecundario' não foi encontrado."
                )
            }
        )
    }

    @Test
    fun `Verificar se herda de Heroi`() {
        val clazz = HeroiSobrenatural::class.java
        val condition = clazz.superclass == Heroi::class.java
        assertTrue(condition, "A classe HeroiSobrenatural não herda de Heroi.")
    }

    @Nested
    @DisplayName("Testes para o método usarPoder")
    inner class usarPoderTests {

        @Test
        fun `usarPoder deve retornar uma mensagem com o nome e o poder principal`() {
            try {
                val clazz = HeroiSobrenatural::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiSobrenatural

                val nome = "Nome do Herói"
                val poderPrincipal = "Poder Principal"

                clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.set(heroi, nome)
                clazz.superclass.getDeclaredField("poderPrincipal").apply { isAccessible = true }
                    .set(heroi, poderPrincipal)

                val metodoUsarPoder = clazz.getDeclaredMethod("usarPoder")

                val mensagem = metodoUsarPoder.invoke(heroi) as String

                assertEquals(
                    "O herói sobrenatural $nome libera seu poder principal: $poderPrincipal!",
                    mensagem,
                    "A mensagem retornada não está correta"
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'usarPoder' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método descrever")
    inner class descreverTests {

        @Test
        fun `descrever deve retornar uma mensagem com o nome, poder principal, poder secundário e nível de energia`() {
            try {
                val clazz = HeroiSobrenatural::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiSobrenatural

                val nome = "Nome do Herói"
                val poderPrincipal = "Poder Principal"
                val poderSecundario = "Poder Secundário"
                val nivelEnergia = 100

                clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.set(heroi, nome)
                clazz.superclass.getDeclaredField("poderPrincipal").apply { isAccessible = true }
                    .set(heroi, poderPrincipal)
                clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }.set(heroi, nivelEnergia)
                clazz.getDeclaredField("poderSecundario").apply { isAccessible = true }.set(heroi, poderSecundario)

                val metodoDescrever = clazz.getDeclaredMethod("descrever")

                val mensagem = metodoDescrever.invoke(heroi) as String

                assertEquals(
                    "Herói $nome: Poder Principal - $poderPrincipal, Poder Secundário - $poderSecundario, Energia - $nivelEnergia.",
                    mensagem,
                    "A mensagem retornada não está correta"
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'descrever' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setPoderSecundario")
    inner class setPoderSecundarioTests {

        @Test
        fun `setPoderSecundario deve alterar o poder secundário se o valor for maior ou igual que 10`() {
            try {
                val clazz = HeroiSobrenatural::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiSobrenatural

                val metodoSetPoderSecundario = clazz.getDeclaredMethod("setPoderSecundario", String::class.java)

                metodoSetPoderSecundario.invoke(heroi, "Poder Secundário")

                val poderSecundario =
                    clazz.getDeclaredField("poderSecundario").apply { isAccessible = true }.get(heroi) as String

                assertEquals("Poder Secundário", poderSecundario, "O poder secundário não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setPoderSecundario' não foi encontrado.")
            }
        }

        @Test
        fun `setPoderSecundario não deve alterar o poder secundário se o valor for menor que 10`() {
            try {
                val clazz = HeroiSobrenatural::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiSobrenatural

                val metodoSetPoderSecundario = clazz.getDeclaredMethod("setPoderSecundario", String::class.java)

                metodoSetPoderSecundario.invoke(heroi, "Poder")

                val poderSecundario =
                    clazz.getDeclaredField("poderSecundario").apply { isAccessible = true }.get(heroi) as String

                assertNotEquals("Poder", poderSecundario, "O poder secundário não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setPoderSecundario' não foi encontrado.")
            }
        }
    }
}