import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Testes de unidade para a classe Filme")
class FilmeFidelidadeTest {

    @Test
    @DisplayName("Verificar se atributos existem, estão com o tipo certo e estão privados")
    fun `verificar se atributos existem, estão com tipo certo e estão privados`() {
        assertAll("Atributos",
            // atributos existem
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("id") },
                    "Não existe o atributo id."
                )
            },
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("nome") },
                    "Não existe o atributo nome."
                )
            },
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("genero") },
                    "Não existe o atributo genero."
                )
            },
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("anoLancamento") },
                    "Não existe o atributo anoLancamento."
                )
            },
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("duracao") },
                    "Não existe o atributo duracao."
                )
            },
            {
                assertDoesNotThrow(
                    { Filme::class.java.getDeclaredField("alugado") },
                    "Não existe o atributo alugado."
                )
            },

            // verificação do tipo do atributo
            {
                assertEquals(
                    Int::class.java,
                    Filme::class.java.getDeclaredField("id").type,
                    "O atributo id não é do tipo Int."
                )
            },
            {
                assertEquals(
                    String::class.java,
                    Filme::class.java.getDeclaredField("nome").type,
                    "O atributo nome não é do tipo String."
                )
            },
            {
                assertEquals(
                    String::class.java,
                    Filme::class.java.getDeclaredField("genero").type,
                    "O atributo genero não é do tipo String."
                )
            },
            {
                assertEquals(
                    Int::class.java,
                    Filme::class.java.getDeclaredField("anoLancamento").type,
                    "O atributo anoLancamento não é do tipo Int."
                )
            },
            {
                assertEquals(
                    Int::class.java,
                    Filme::class.java.getDeclaredField("duracao").type,
                    "O atributo duracao não é do tipo Int."
                )
            },
            {
                assertEquals(
                    Boolean::class.java,
                    Filme::class.java.getDeclaredField("alugado").type,
                    "O atributo alugado não é do tipo Boolean."
                )
            },

            // verificar se é privado
            {
                assertTrue(
                    Filme::class.java.getDeclaredField("nome").modifiers and 2 != 0,
                    "O atributo nome não é privado."
                )
            },
            {
                assertTrue(
                    Filme::class.java.getDeclaredField("genero").modifiers and 2 != 0,
                    "O atributo genero não é privado."
                )
            },
            {
                assertTrue(
                    Filme::class.java.getDeclaredField("anoLancamento").modifiers and 2 != 0,
                    "O atributo anoLancamento não é privado."
                )
            },
            {
                assertTrue(
                    Filme::class.java.getDeclaredField("duracao").modifiers and 2 != 0,
                    "O atributo duracao não é privado."
                )
            },
            {
                assertTrue(
                    Filme::class.java.getDeclaredField("alugado").modifiers and 2 != 0,
                    "O atributo alugado não é privado."
                )
            }
        )
    }

//    @Test
//    @DisplayName("Verificar se métodos existem e estão com o tipo certo")
//    fun `verificar se métodos existem e estão com o tipo certo`() {
//        assertAll("Métodos",
//            // métodos existem
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("setNome", String::class.java),
//                    "Não existe o método setNome."
//                )
//            },
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("setGenero", String::class.java),
//                    "Não existe o método setGenero."
//                )
//            },
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("setAnoLancamento", Int::class.java),
//                    "Não existe o método setAnoLancamento."
//                )
//            },
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("setDuracao", Int::class.java),
//                    "Não existe o método setDuracao."
//                )
//            },
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("setAlugado", Boolean::class.java),
//                    "Não existe o método setAlugado."
//                )
//            },
//            {
//                assertNotNull(
//                    Filme::class.java.getDeclaredMethod("descrever"),
//                    "Não existe o método descrever."
//                )
//            },
//
//            // verificação do tipo do método
//            {
//                assertEquals(
//                    Void.TYPE,
//                    Filme::class.java.getDeclaredMethod("setNome", String::class.java).returnType,
//                    "O método setNome não é do tipo 'sem retorno (Void)'."
//                )
//            },
//            {
//                assertEquals(
//                    Void.TYPE,
//                    Filme::class.java.getDeclaredMethod("setGenero", String::class.java).returnType,
//                    "O método setGenero não é do tipo 'sem retorno (Void)'."
//                )
//            },
//            {
//                assertEquals(
//                    Void.TYPE,
//                    Filme::class.java.getDeclaredMethod("setAnoLancamento", Int::class.java).returnType,
//                    "O método setAnoLancamento não é do tipo 'sem retorno (Void)'."
//                )
//            },
//            {
//                assertEquals(
//                    Void.TYPE,
//                    Filme::class.java.getDeclaredMethod("setDuracao", Int::class.java).returnType,
//                    "O método setDuracao não é do tipo 'sem retorno (Void)'."
//                )
//            },
//            {
//                assertEquals(
//                    Void.TYPE,
//                    Filme::class.java.getDeclaredMethod("setAlugado", Boolean::class.java).returnType,
//                    "O método setAlugado não é do tipo 'sem retorno (Void)'."
//                )
//            },
//            {
//                assertEquals(
//                    String::class.java,
//                    Filme::class.java.getDeclaredMethod("descrever").returnType,
//                    "O método descrever não está retornando String"
//                )
//            },
//            {
//                assertEquals(
//                    String::class.java,
//                    Filme::class.java.getDeclaredMethod("converterDuracaoEmHoras").returnType,
//                    "O método converterDuracaoEmHoras não está retornando String."
//                )
//            }
//        )
//    }

    @Nested
    @DisplayName("Testes para o método setNome")
    inner class SetNomeTests {

        @Test
        @DisplayName("Método setNome deve alterar o nome do filme caso o novo nome tenha mínimo de 2 caracteres")
        fun `setNome deve alterar o nome do filme caso o novo nome tenha mínimo de 2 caracteres`() {

            try {

                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(filme, "Matrix")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(filme) as String

                assertEquals("Matrix", nome, "O nome do filme não foi alterado corretamente.")
            } catch (e: NoSuchMethodException) {
                fail("O método setNome não foi encontrado na classe Filme.")
            }
        }

        @Test
        @DisplayName("Método setNome não deve alterar o nome do filme caso o novo nome tenha menos de 2 caracteres")
        fun `setNome não deve alterar o nome do filme caso o novo nome tenha menos de 2 caracteres`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetNome = clazz.getDeclaredMethod("setNome", String::class.java)

                metodoSetNome.invoke(filme, "M")

                val nome = clazz.getDeclaredField("nome").apply { isAccessible = true }.get(filme) as String

                assertEquals("", nome, "O nome do filme não deveria ter sido alterado.")
            } catch (e: NoSuchMethodException) {
                fail("O método setNome não foi encontrado na classe Filme.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setGenero")
    inner class SetGeneroTests {

        @Test
        @DisplayName("Método setGenero deve alterar o gênero do filme caso o novo gênero tenha mínimo de 2 caracteres")
        fun `setGenero deve alterar o gênero do filme caso o novo gênero tenha mínimo de 2 caracteres`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetGenero = clazz.getDeclaredMethod("setGenero", String::class.java)

                metodoSetGenero.invoke(filme, "Ação")

                val genero = clazz.getDeclaredField("genero").apply { isAccessible = true }.get(filme) as String

                assertEquals("Ação", genero, "O gênero do filme não foi alterado corretamente.")
            } catch (e: NoSuchMethodException) {
                fail("O método setGenero não foi encontrado na classe Filme.")
            }
        }

        @Test
        @DisplayName("Método setGenero não deve alterar o gênero do filme caso o novo gênero tenha menos de 2 caracteres")
        fun `setGenero não deve alterar o gênero do filme caso o novo gênero tenha menos de 2 caracteres`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetGenero = clazz.getDeclaredMethod("setGenero", String::class.java)

                metodoSetGenero.invoke(filme, "A")

                val genero = clazz.getDeclaredField("genero").apply { isAccessible = true }.get(filme) as String

                assertEquals("", genero, "O gênero do filme não deveria ter sido alterado.")
            } catch (e: NoSuchMethodException) {
                fail("O método setGenero não foi encontrado na classe Filme.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setAnoLancamento")
    inner class SetAnoLancamentoTests {

        @Test
        @DisplayName("Método setAnoLancamento deve alterar o ano de lançamento do filme caso o novo ano seja maior ou igual a 1895")
        fun `setAnoLancamento deve alterar o ano de lançamento do filme caso o novo ano seja maior ou igual a 1895`() {

            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetAnoLancamento = clazz.getDeclaredMethod("setAnoLancamento", Int::class.java)

                metodoSetAnoLancamento.invoke(filme, 2000)

                val anoLancamento =
                    clazz.getDeclaredField("anoLancamento").apply { isAccessible = true }.get(filme) as Int

                assertEquals(2000, anoLancamento, "O ano de lançamento do filme não foi alterado corretamente.")
            } catch (e: NoSuchMethodException) {
                fail("O método setAnoLancamento não foi encontrado na classe Filme.")
            }
        }

        @Test
        @DisplayName("Método setAnoLancamento não deve alterar o ano de lançamento do filme caso o novo ano seja menor que 1895")
        fun `setAnoLancamento não deve alterar o ano de lançamento do filme caso o novo ano seja menor que 1895`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetAnoLancamento = clazz.getDeclaredMethod("setAnoLancamento", Int::class.java)

                metodoSetAnoLancamento.invoke(filme, 1894)

                val anoLancamento =
                    clazz.getDeclaredField("anoLancamento").apply { isAccessible = true }.get(filme) as Int

                assertEquals(
                    0,
                    anoLancamento,
                    "O ano de lançamento do filme não deveria ter sido alterado. Valide o mínimo de 1895."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método setAnoLancamento não foi encontrado na classe Filme.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setDuracao")
    inner class SetDuracaoTests {

        @Test
        @DisplayName("Método setDuracao deve alterar a duração do filme caso a nova duração seja maior ou igual a 15")
        fun `setDuracao deve alterar a duração do filme caso a nova duração seja maior ou igual a 15`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetDuracao = clazz.getDeclaredMethod("setDuracao", Int::class.java)

                metodoSetDuracao.invoke(filme, 120)

                val duracao = clazz.getDeclaredField("duracao").apply { isAccessible = true }.get(filme) as Int

                assertEquals(120, duracao, "A duração do filme não foi alterada corretamente.")
            } catch (e: NoSuchMethodException) {
                fail("O método setDuracao não foi encontrado na classe Filme.")
            }
        }

        @Test
        @DisplayName("Método setDuracao não deve alterar a duração do filme caso a nova duração seja menor do que 15")
        fun `setDuracao não deve alterar a duração do filme caso a nova duração seja menor do que 15`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetDuracao = clazz.getDeclaredMethod("setDuracao", Int::class.java)

                metodoSetDuracao.invoke(filme, 10)

                val duracao = clazz.getDeclaredField("duracao").apply { isAccessible = true }.get(filme) as Int

                assertEquals(
                    0,
                    duracao,
                    "A duração do filme não deveria ter sido alterada. Verifique o mínimo de 15 minutos."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método setDuracao não foi encontrado na classe Filme.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método setAlugado")
    inner class SetAlugadoTests {

        @Test
        @DisplayName("Método setAlugado deve alterar o status de alugado do filme")
        fun `setAlugado deve alterar o status de alugado do filme`() {
            try {

                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetAlugado = clazz.getDeclaredMethod("setAlugado", Boolean::class.java)

                metodoSetAlugado.invoke(filme, true)

                val alugado =
                    clazz.getDeclaredField("alugado").apply { isAccessible = true }.get(filme) as Boolean

                assertTrue(alugado, "O status de alugado do filme não foi alterado corretamente.")
            } catch (e: NoSuchMethodException) {
                fail("O método setAlugado não foi encontrado na classe Filme.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método descrever")
    inner class DescreverTests {

        @Test
        @DisplayName("Método descrever deve retornar uma string com os dados do filme")
        fun `descrever deve retornar uma string com os dados do filme`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val descrever = clazz.getDeclaredMethod("descrever").invoke(filme) as String

                assertTrue(descrever.contains("Id"), "A descrição do filme não contém o id.")
                assertTrue(descrever.contains("Nome"), "A descrição do filme não contém o nome.")
                assertTrue(descrever.contains("Gênero"), "A descrição do filme não contém o gênero.")
                assertTrue(descrever.contains("Ano"), "A descrição do filme não contém o ano de lançamento.")
                assertTrue(
                    descrever.contains("Alugado"),
                    "A descrição do filme não contém o status de alugado (sim ou não)."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método descrever não foi encontrado na classe Filme.")
            }

            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val fieldDuracao = clazz.getDeclaredField("duracao").apply { isAccessible = true }
                fieldDuracao.set(filme, 90)

                val descrever = clazz.getDeclaredMethod("descrever").invoke(filme) as String

                assertTrue(descrever.contains("Duração"), "A descrição do filme não contém a duração.")
                assertTrue(
                    descrever.contains("01:30"),
                    "A descrição do filme não contém a duração correta. Verifique se a duração está sendo exibida convertida."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método converterDuracaoEmHoras não foi encontrado na classe Filme. É preciso implementar o método para que a descrição do filme seja correta.")
            }
        }
    }

    @Nested
    @DisplayName("Testes para o método converterDuracaoEmHoras")
    inner class ConverterDuracaoEmHorasTests {

        @Test
        @DisplayName("Método converterDuracaoEmHoras deve retornar a duração do filme em horas (120 minutos)")
        fun `converterDuracaoEmHoras deve retornar a duração do filme em horas`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetDuracao = clazz.getDeclaredMethod("setDuracao", Int::class.java)
                metodoSetDuracao.invoke(filme, 120)

                val converterDuracaoEmHoras =
                    clazz.getDeclaredMethod("converterDuracaoEmHoras").invoke(filme) as String

                assertEquals(
                    "02:00",
                    converterDuracaoEmHoras,
                    "O método não retornou a mensagem correta. Verifique se a mensagem é a exatamente a mesma."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método converterDuracaoEmHoras não foi encontrado na classe Filme.")
            }
        }

        @Test
        @DisplayName("Método converterDuracaoEmHoras deve retornar a duração do filme em horas (150 minutos)")
        fun `converterDuracaoEmHoras deve retornar a duração do filme em horas (150 minutos)`() {
            try {
                val clazz = Filme::class.java
                val filme = clazz.getDeclaredConstructor().newInstance() as Filme

                val metodoSetDuracao = clazz.getDeclaredMethod("setDuracao", Int::class.java)
                metodoSetDuracao.invoke(filme, 150)

                val converterDuracaoEmHoras =
                    clazz.getDeclaredMethod("converterDuracaoEmHoras").invoke(filme) as String

                assertEquals(
                    "02:30",
                    converterDuracaoEmHoras,
                    "O método não retornou a mensagem correta. Verifique se a mensagem é a exatamente a mesma."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método converterDuracaoEmHoras não foi encontrado na classe Filme.")
            }
        }
    }
}