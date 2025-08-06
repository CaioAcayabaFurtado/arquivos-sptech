import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe Heroi")
class HeroiFidelidadeTest {

    @Test
    fun `Verificar se a classe abstrato Heroi foi implementada corretamente`() {
        val clazz = Heroi::class.java
        val modifier = clazz.modifiers
        val condition = modifier and java.lang.reflect.Modifier.ABSTRACT != 0
        assertTrue(condition, "A classe Heroi não é abstrata.")
    }

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredField("nome") },
                    "Atributo 'nome' não existe."
                )
            },
            {
                assertTrue(
                    { Heroi::class.java.getDeclaredField("nome").type == String::class.java },
                    "Atributo 'nome' não é do tipo String"
                )
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredField("poderPrincipal") },
                    "Atributo 'poderPrincipal' não existe."
                )
            },
            {
                assertTrue(
                    { Heroi::class.java.getDeclaredField("poderPrincipal").type == String::class.java },
                    "Atributo 'poderPrincipal' não é do tipo String"
                )
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredField("nivelEnergia") },
                    "Atributo 'nivelEnergia' não existe."
                )
            },
            {
                assertTrue(
                    { Heroi::class.java.getDeclaredField("nivelEnergia").type == Int::class.java },
                    "Atributo 'nivelEnergia' não é do tipo Int"
                )
            }
        )
    }

    @Test
    fun `Verificar se métodos são abstratos e retornam certo`() {
        assertAll("Métodos",
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("usarPoder") },
                    "Método 'usarPoder' não existe."
                )
            },
            {
                val clazz = Heroi::class.java
                val modifier = clazz.getDeclaredMethod("usarPoder").modifiers
                val condition = modifier and java.lang.reflect.Modifier.ABSTRACT != 0
                assertTrue(condition, "Método 'usarPoder' não é abstrato.")
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("descrever") },
                    "Método 'descrever' não existe."
                )
            },
            {
                val clazz = Heroi::class.java
                val modifier = clazz.getDeclaredMethod("descrever").modifiers
                val condition = modifier and java.lang.reflect.Modifier.ABSTRACT != 0
                assertTrue(condition, "Método 'descrever' não é abstrato.")
            }
        )
    }

    @Test
    fun `verificar se métodos concretos existem`() {
        assertAll("Métodos",
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("recuperarEnergia") },
                    "Método 'recuperarEnergia' não existe."
                )
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("setNome", String::class.java) },
                    "Método 'setNome' não existe."
                )
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("setPoderPrincipal", String::class.java) },
                    "Método 'setPoderPrincipal' não existe."
                )
            },
            {
                assertDoesNotThrow(
                    { Heroi::class.java.getDeclaredMethod("setNivelEnergia", Int::class.java) },
                    "Método 'setNivelEnergia' não existe."
                )
            }
        )
    }
}