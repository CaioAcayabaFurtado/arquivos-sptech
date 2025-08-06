import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HeroiFidelidadeImplementacaoTest {

    @Nested
    @DisplayName("Testes para a implementação do método recuperarEnergia da classe herdade Heroi")
    inner class recuperarEnergiaTests {
        @Test
        fun `recuperarEnergia deve retornar a mensagem correta`() {
            try {

                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                clazz.superclass.getDeclaredField("nome").let {
                    it.isAccessible = true
                    it.set(heroi, "Tony Stark")
                }

                clazz.superclass.getDeclaredField("nivelEnergia").let {
                    it.isAccessible = true
                    it.set(heroi, 100)
                }

                val metodoRecuperarEnergia = clazz.superclass.getDeclaredMethod("recuperarEnergia")
                val result = metodoRecuperarEnergia.invoke(heroi) as String

                val nome = clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.get(heroi) as String
                val nivelEnergia =
                    clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }.get(heroi) as Int

                assertEquals(
                    "O heroi $nome está recuperando energia e agora tem $nivelEnergia pontos.",
                    result,
                    "A mensagem retornada não é a esperada"
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'recuperarEnergia' não foi encontrado na classe Heroi.")
            }
        }

        @Test
        fun `recuperarEnergia deve aumentar o nível de energia em 20 pontos`() {
            try {

                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val nivelEnergia = clazz.superclass.getDeclaredField("nivelEnergia").apply {
                    isAccessible = true
                    set(heroi, 100)
                }

                val metodoRecuperarEnergia = clazz.superclass.getDeclaredMethod("recuperarEnergia")

                metodoRecuperarEnergia.invoke(heroi)

                val nivelEnergiaAtual = nivelEnergia.get(heroi) as Int

                assertEquals(120, nivelEnergiaAtual, "O nível de energia não foi aumentado em 20 pontos")

            } catch (e: NoSuchMethodException) {
                fail("O método 'recuperarEnergia' não foi encontrado na classe Heroi.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para a implementação do método setNome da classe herdade Heroi")
    inner class setNomeTests {
        @Test
        fun `setNome deve alterar o nome se o valor for maior ou igual que 3`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoNome = "Tony Stark"
                val metodoSetNome = clazz.superclass.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(heroi, novoNome)

                val nome = clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.get(heroi) as String

                assertEquals(novoNome, nome, "O nome não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado na classe Heroi.")
            }
        }

        @Test
        fun `setNome não deve alterar o nome se o valor for menor que 3`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoNome = "Te"
                val metodoSetNome = clazz.superclass.getDeclaredMethod("setNome", String::class.java)

                val nome = clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.get(heroi) as String

                metodoSetNome.invoke(heroi, novoNome)

                val nomeAtual =
                    clazz.superclass.getDeclaredField("nome").apply { isAccessible = true }.get(heroi) as String

                assertEquals(nome, nomeAtual, "O nome não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado na classe Heroi.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para a implementação do método setPoderPrincipal da classe herdade Heroi")
    inner class setPoderPrincipalTests {
        @Test
        fun `setPoderPrincipal deve alterar o poder principal se o valor for maior ou igual que 10`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoPoderPrincipal = "Armadura Mark 50"
                val metodoSetPoderPrincipal =
                    clazz.superclass.getDeclaredMethod("setPoderPrincipal", String::class.java)

                metodoSetPoderPrincipal.invoke(heroi, novoPoderPrincipal)

                val poderPrincipal = clazz.superclass.getDeclaredField("poderPrincipal").apply { isAccessible = true }
                    .get(heroi) as String

                assertEquals(novoPoderPrincipal, poderPrincipal, "O poder principal não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setPoderPrincipal' não foi encontrado na classe Heroi.")
            }
        }

        @Test
        fun `setPoderPrincipal não deve alterar o poder principal se o valor for menor que 10`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoPoderPrincipal = "Armadura"
                val metodoSetPoderPrincipal =
                    clazz.superclass.getDeclaredMethod("setPoderPrincipal", String::class.java)

                val poderPrincipal = clazz.superclass.getDeclaredField("poderPrincipal").apply { isAccessible = true }
                    .get(heroi) as String

                metodoSetPoderPrincipal.invoke(heroi, novoPoderPrincipal)

                val poderPrincipalAtual =
                    clazz.superclass.getDeclaredField("poderPrincipal").apply { isAccessible = true }
                        .get(heroi) as String

                assertEquals(poderPrincipal, poderPrincipalAtual, "O poder principal não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setPoderPrincipal' não foi encontrado na classe Heroi.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para a implementação do método setNivelEnergia da classe herdade Heroi")
    inner class setNivelEnergiaTests {
        @Test
        fun `setNivelEnergia deve alterar o nível de energia se o valor for maior ou igual que 0`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoNivelEnergia = 100
                val metodoSetNivelEnergia = clazz.superclass.getDeclaredMethod("setNivelEnergia", Int::class.java)

                metodoSetNivelEnergia.invoke(heroi, novoNivelEnergia)

                val nivelEnergia = clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }
                    .get(heroi) as Int

                assertEquals(novoNivelEnergia, nivelEnergia, "O nível de energia não foi alterado corretamente")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelEnergia' não foi encontrado na classe Heroi.")
            }
        }

        @Test
        fun `setNivelEnergia não deve alterar o nível de energia se o valor for menor que 0`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoNivelEnergia = -10
                val metodoSetNivelEnergia = clazz.superclass.getDeclaredMethod("setNivelEnergia", Int::class.java)

                val nivelEnergia = clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }
                    .get(heroi) as Int

                metodoSetNivelEnergia.invoke(heroi, novoNivelEnergia)

                val nivelEnergiaAtual = clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }
                    .get(heroi) as Int

                assertEquals(nivelEnergia, nivelEnergiaAtual, "O nível de energia não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelEnergia' não foi encontrado na classe Heroi.")
            }
        }

        @Test
        fun `setNivelEnergia não deve alterar o nível de energia se o valor for igual a 0`() {
            try {
                val clazz = HeroiTecnologico::class.java
                val heroi = clazz.getDeclaredConstructor().newInstance() as HeroiTecnologico

                val novoNivelEnergia = 0
                val metodoSetNivelEnergia = clazz.superclass.getDeclaredMethod("setNivelEnergia", Int::class.java)

                val nivelEnergia = clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }
                    .get(heroi) as Int

                metodoSetNivelEnergia.invoke(heroi, novoNivelEnergia)

                val nivelEnergiaAtual = clazz.superclass.getDeclaredField("nivelEnergia").apply { isAccessible = true }
                    .get(heroi) as Int

                assertEquals(nivelEnergia, nivelEnergiaAtual, "O nível de energia não deveria ter sido alterado")

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNivelEnergia' não foi encontrado na classe Heroi.")
            }
        }
    }
}