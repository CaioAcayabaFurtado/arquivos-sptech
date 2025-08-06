import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de fidelidade da classe Personagem")
class PersonagemFidelidadeTest {

    @Test
    fun `Verificar se atributos existem e estão com o tipo certo`() {
        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    {Personagem::class.java.getDeclaredField("habilidades") },
                    "Atributo 'habilidades' não existe."
                )
            },
            {
                assertTrue(
                    {Personagem::class.java.getDeclaredField("habilidades").type == MutableList::class.java},
                    "Atributo 'habilidades' não é do tipo MutableList ou não recebe mutableListOf<Habilidade> como valor padrão"
                )
            }
        )
    }

    @Nested
    @DisplayName("Testes para o método adicionarHabilidade")
    inner class adicionarHabilidadeTests {
        @Test
        fun `adicionarHabilidade deve adicionar uma habilidade à lista de habilidades`() {
            try {
                val clazz = Personagem::class.java
                val personagem = clazz.getDeclaredConstructor().newInstance() as Personagem

                val metodoAdicionarHabilidade = clazz.getDeclaredMethod("adicionarHabilidade", Habilidade::class.java)
                val habilidade = HabilidadeFixture.umaHabilidade()

                metodoAdicionarHabilidade.invoke(personagem, habilidade)

                val habilidades = clazz.getDeclaredField("habilidades").apply { isAccessible = true }.get(personagem) as MutableList<Habilidade>

                assertTrue(habilidades.contains(habilidade), "A habilidade não foi adicionada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'adicionarHabilidade' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método removerHabilidade")
    inner class removerHabilidadePorNomeTests {
        @Test
        fun `removerHabilidadePorNome deve remover uma habilidade da lista de habilidades`() {
            try {
                val clazz = Personagem::class.java
                val personagem = clazz.getDeclaredConstructor().newInstance() as Personagem

                val metodoAdicionarHabilidade = clazz.getDeclaredMethod("adicionarHabilidade", Habilidade::class.java)
                val metodoRemoverHabilidade = clazz.getDeclaredMethod("removerHabilidade", String::class.java)
                val habilidade = HabilidadeFixture.umaHabilidade()

                metodoAdicionarHabilidade.invoke(personagem, habilidade)
                metodoRemoverHabilidade.invoke(personagem, "Ataque")

                val habilidades = clazz.getDeclaredField("habilidades").apply { isAccessible = true }.get(personagem) as MutableList<Habilidade>

                assertFalse(habilidades.contains(habilidade), "A habilidade não foi removida corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'removerHabilidadePorNome' não foi encontrado.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método listarHabilidades")
    inner class listarHabilidadesTests {
        @Test
        fun `listarHabilidades deve retornar a lista de habilidades do personagem`() {
            try {
                val clazz = Personagem::class.java
                val personagem = clazz.getDeclaredConstructor().newInstance() as Personagem

                val metodoAdicionarHabilidade = clazz.getDeclaredMethod("adicionarHabilidade", Habilidade::class.java)
                val metodoListarHabilidades = clazz.getDeclaredMethod("listarHabilidades")

                val habilidade = HabilidadeFixture.umaHabilidade()

                metodoAdicionarHabilidade.invoke(personagem, habilidade)

                val habilidades = metodoListarHabilidades.invoke(personagem) as List<Habilidade>

                assertTrue(habilidades.contains(habilidade), "A habilidade não foi listada corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'listarHabilidades' não foi encontrado.")
            }
        }
    }

    @Test
    fun `verificar se a classe Personagem herda de Pessoa`() {
        assertTrue(Personagem::class.java.superclass == Pessoa::class.java, "Personagem não herda de Pessoa")
    }
}