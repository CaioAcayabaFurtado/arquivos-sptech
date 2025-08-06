import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade para classe HeroiTecnologico")
class HeroiTecnologicoFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {

        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { HeroiTecnologico::class.java.getDeclaredField("gadgetAtual") },
                    "Atributo 'gadgetAtual' não existe."
                )
            },
            {
                assertTrue(
                    { HeroiTecnologico::class.java.getDeclaredField("gadgetAtual").type == String::class.java },
                    "Atributo 'gadgetAtual' não é do tipo String"
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
                    { HeroiTecnologico::class.java.getDeclaredMethod("usarPoder") },
                    "Método 'usarPoder' não foi encontrado."
                )
            },
            {
                assertDoesNotThrow(
                    { HeroiTecnologico::class.java.getDeclaredMethod("descrever") },
                    "Método 'descrever' não foi encontrado."
                )
            },
            {
                assertDoesNotThrow(
                    { HeroiTecnologico::class.java.getDeclaredMethod("equiparGadget", String::class.java) },
                    "Método 'equiparGadget' não foi encontrado."
                )
            },
            {
                assertDoesNotThrow(
                    { HeroiTecnologico::class.java.getDeclaredMethod("usarGadget") },
                    "Método 'usarGadget' não foi encontrado."
                )
            }
        )
    }

    @Test
    fun `Verificar se herda de Heroi`() {
        val clazz = HeroiTecnologico::class.java
        val superclass = clazz.genericSuperclass
        val condition = superclass.typeName == "Heroi"
        assertTrue(condition, "A classe HeroiTecnologico não herda de Heroi.")
    }

    @Test
    fun `Verificar se implementa a interface UsuarioGadgets`() {
        val clazz = HeroiTecnologico::class.java
        val interfaces = clazz.interfaces
        val condition = interfaces.any { it.typeName == "UsuarioGadgets" }
        assertTrue(condition, "A classe HeroiTecnologico não implementa a interface UsuarioGadgets.")
    }

    @Nested
    @DisplayName("Testes para o método usarPoder implementado")
    inner class usarPoderTests {
        @Test
        fun `usarPoder deve retornar a mensagem correta`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroiTecnologico = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val nome = "Tony Stark"
                val poderPrincipal = "Armadura de Ferro"

                clazz.superclass.getDeclaredField("nome").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, nome)
                }

                clazz.superclass.getDeclaredField("poderPrincipal").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, poderPrincipal)
                }

                val metodoUsarPoder = clazz.getDeclaredMethod("usarPoder")

                val result = metodoUsarPoder.invoke(heroiTecnologico) as String

                assertEquals(
                    "O herói tecnológico $nome usa seu poder: $poderPrincipal, auxiliado por tecnologia avançada!",
                    result,
                    "A mensagem retornada não está correta."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'usarPoder' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método descrever implementado")
    inner class descreverTests {
        @Test
        fun `descrever deve retornar a descrição correta`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroiTecnologico = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val nome = "Tony Stark"
                val poderPrincipal = "Armadura de Ferro"
                val nivelEnergia = 100

                clazz.superclass.getDeclaredField("nome").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, nome)
                }

                clazz.superclass.getDeclaredField("poderPrincipal").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, poderPrincipal)
                }

                clazz.superclass.getDeclaredField("nivelEnergia").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, nivelEnergia)
                }

                val metodoDescrever = clazz.getDeclaredMethod("descrever")

                val result = metodoDescrever.invoke(heroiTecnologico) as String

                assertEquals(
                    "Herói $nome: Poder Principal - $poderPrincipal, Energia - $nivelEnergia.",
                    result,
                    "A descrição retornada não está correta."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'descrever' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método equiparGadget implementado")
    inner class equiparGadgetTests {
        @Test
        fun `equiparGadget deve retornar a mensagem correta`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroiTecnologico = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val nome = "Tony Stark"
                val gadget = "Reator Arc"

                clazz.superclass.getDeclaredField("nome").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, nome)
                }

                val metodoEquiparGadget = clazz.getDeclaredMethod("equiparGadget", String::class.java)

                val result = metodoEquiparGadget.invoke(heroiTecnologico, gadget) as String

                assertEquals(
                    "Gadget $gadget equipado pelo herói $nome.",
                    result,
                    "A mensagem retornada não está correta."
                )

                val gadgetAtual = clazz.getDeclaredField("gadgetAtual").let {
                    it.isAccessible = true
                    it.get(heroiTecnologico) as String
                }

                assertEquals(
                    gadget,
                    gadgetAtual,
                    "O gadget atual não foi alterado corretamente."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'equiparGadget' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método usarGadget implementado")
    inner class usarGadgetTests {
        @Test
        fun `usarGadget deve retornar a mensagem correta se houver gadget equipado`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroiTecnologico = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val gadget = "Reator Arc"

                clazz.getDeclaredField("gadgetAtual").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, gadget)
                }

                val metodoUsarGadget = clazz.getDeclaredMethod("usarGadget")

                val result = metodoUsarGadget.invoke(heroiTecnologico) as String

                assertEquals(
                    "O gadget $gadget foi ativado!",
                    result,
                    "A mensagem retornada não está correta."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'usarGadget' não foi encontrado.")
            }
        }

        @Test
        fun `usarGadget deve retornar a mensagem correta se não houver gadget equipado`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroiTecnologico = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                clazz.getDeclaredField("gadgetAtual").let {
                    it.isAccessible = true
                    it.set(heroiTecnologico, "")
                }

                val metodoUsarGadget = clazz.getDeclaredMethod("usarGadget")

                val result = metodoUsarGadget.invoke(heroiTecnologico) as String

                assertEquals(
                    "Nenhum gadget equipado",
                    result,
                    "A mensagem retornada não está correta."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'usarGadget' não foi encontrado.")
            }
        }
    }
}