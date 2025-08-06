import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para a interface UsuarioGadgets")
class UsuarioGadgetsFidelidadeTest {

    @Test
    fun `Verificar se a interface UsuarioGadgets foi implementada corretamente`() {
        val clazz = UsuarioGadgets::class.java
        val condition = clazz.isInterface
        assertTrue(condition, "A classe UsuarioGadgets não é uma interface.")
    }

    @Test
    fun `Verificar se métodos são abstratos e retornam certo`() {
        val clazz = UsuarioGadgets::class.java
        val metodos = clazz.declaredMethods

        metodos.forEach {
            val modifier = it.modifiers
            val condition = modifier and java.lang.reflect.Modifier.ABSTRACT != 0
            assertTrue(condition, "O método ${it.name} não é abstrato.")
        }
    }
}