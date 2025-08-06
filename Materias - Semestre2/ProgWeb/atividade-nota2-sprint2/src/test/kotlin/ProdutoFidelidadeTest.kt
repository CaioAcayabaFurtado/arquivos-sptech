import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.fail

@DisplayName("Testes para a classe de produto")
class ProdutoFidelidadeTest {

    @Test
    @DisplayName("Verificar se o atributos existem")
    fun `verificar se o atributos existem`() {
        assertAll("Atributos",
            {
                assertDoesNotThrow(
                    { Produto::class.java.getDeclaredField("nome") },
                    "O atributo 'nome' não foi encontrado na classe Produto"
                )
            },
            {
                assertDoesNotThrow(
                    { Produto::class.java.getDeclaredField("categoria") },
                    "O atributo 'categoria' não foi encontrado na classe Produto"
                )
            },
            {
                assertDoesNotThrow(
                    { Produto::class.java.getDeclaredField("preco") },
                    "O atributo 'preco' não foi encontrado na classe Produto"
                )
            },
            {
                assertDoesNotThrow(
                    { Produto::class.java.getDeclaredField("fabricante") },
                    "O atributo 'fabricante' não foi encontrado na classe Produto"
                )
            }
        )
    }

    @Nested
    @DisplayName("Cenários do método setNome")
    inner class SetNome {

        @Test
        @DisplayName("Verificar se o método 'setNome' existe")
        fun `verificar se o método 'setNome' existe`() {
            try {
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setNome", String::class.java)
                assertNotNull(method, "O método 'setNome' não foi encontrado na classe Produto")
                assertEquals(Void.TYPE, method.returnType, "O tipo de retorno do método 'setNome' está incorreto.")

                val produto = Produto()
                method.invoke(produto, "Nome Produto")

                val field = clazz.getDeclaredField("nome")
                field.isAccessible = true

                assertEquals(
                    "Nome Produto",
                    field.get(produto),
                    "O método 'setNome' não está alterando o atributo 'nome' corretamente. Valide se o nome é maior que 3 caracteres."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado na classe Produto")
            }
        }

        @Test
        @DisplayName("Verificar se o método 'setNome' valida o tamanho do nome")
        fun `verificar se o método 'setNome' valida o tamanho do nome`() {
            try {


                val produto = Produto()
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setNome", String::class.java)
                method.invoke(produto, "AAA")

                val field = clazz.getDeclaredField("nome")
                field.isAccessible = true

                assertEquals(
                    "",
                    field.get(produto),
                    "O método 'setNome' não está validando o tamanho do nome corretamente. Valide se o nome é maior que 3 caracteres."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'setNome' não foi encontrado na classe Produto")
            }
        }

    }

    @Nested
    @DisplayName("Cenários do método setCategoria")
    inner class SetCategoria {

        @Test
        @DisplayName("Verificar se o método 'setCategoria' existe")
        fun `verificar se o método 'setCategoria' existe`() {
            try {
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setCategoria", String::class.java)
                assertNotNull(method, "O método 'setCategoria' não foi encontrado na classe Produto")
                assertEquals(Void.TYPE, method.returnType, "O tipo de retorno do método 'setCategoria' está incorreto.")

                val produto = Produto()
                method.invoke(produto, "Categoria Produto")

                val field = clazz.getDeclaredField("categoria")
                field.isAccessible = true

                assertEquals(
                    "Categoria Produto",
                    field.get(produto),
                    "O método 'setCategoria' não está alterando o atributo 'categoria' corretamente. Valide se a categoria é maior que 3 caracteres."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setCategoria' não foi encontrado na classe Produto")
            }
        }

        @Test
        @DisplayName("Verificar se o método 'setCategoria' valida o tamanho da categoria")
        fun `verificar se o método 'setCategoria' valida o tamanho da categoria`() {
            try {


                val produto = Produto()
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setCategoria", String::class.java)
                method.invoke(produto, "AAAAA")

                val field = clazz.getDeclaredField("categoria")
                field.isAccessible = true

                assertEquals(
                    "",
                    field.get(produto),
                    "O método 'setCategoria' não está validando o tamanho da categoria corretamente. Valide se a categoria é maior que 3 caracteres."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'setCategoria' não foi encontrado na classe Produto")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método setPreco")
    inner class SetPreco {

        @Test
        @DisplayName("Verificar se o método 'setPreco' existe")
        fun `verificar se o método 'setPreco' existe`() {
            try {
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setPreco", Double::class.java)
                assertNotNull(method, "O método 'setPreco' não foi encontrado na classe Produto")
                assertEquals(Void.TYPE, method.returnType, "O tipo de retorno do método 'setPreco' está incorreto.")

                val produto = Produto()
                method.invoke(produto, 10.0)

                val field = clazz.getDeclaredField("preco")
                field.isAccessible = true

                assertEquals(
                    10.0,
                    field.get(produto),
                    "O método 'setPreco' não está alterando o atributo 'preco' corretamente. Valide se o preço é maior que 0."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setPreco' não foi encontrado na classe Produto")
            }
        }

        @Test
        @DisplayName("Verificar se o método 'setPreco' valida o preço")
        fun `verificar se o método 'setPreco' valida o preço`() {
            try {
                val produto = Produto()
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setPreco", Double::class.java)
                method.invoke(produto, -1.0)

                val field = clazz.getDeclaredField("preco")
                field.isAccessible = true

                assertEquals(
                    0.0,
                    field.get(produto),
                    "O método 'setPreco' não está validando o preço corretamente. Valide se o preço é maior que 0."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'setPreco' não foi encontrado na classe Produto")
            }
        }
    }

    @Nested
    @DisplayName("Cenários do método setFabricante")
    inner class SetFabricante {

        @Test
        @DisplayName("Verificar se o método 'setFabricante' existe")
        fun `verificar se o método 'setFabricante' existe`() {
            try {
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setFabricante", String::class.java)
                assertNotNull(method, "O método 'setFabricante' não foi encontrado na classe Produto")
                assertEquals(
                    Void.TYPE,
                    method.returnType,
                    "O tipo de retorno do método 'setFabricante' está incorreto."
                )

                val produto = Produto()
                method.invoke(produto, "Fabricante Produto")

                val field = clazz.getDeclaredField("fabricante")
                field.isAccessible = true

                assertEquals(
                    "Fabricante Produto",
                    field.get(produto),
                    "O método 'setFabricante' não está alterando o atributo 'fabricante' corretamente. Valide se o fabricante é maior que 3 caracteres."
                )

            } catch (e: NoSuchMethodException) {
                fail("O método 'setFabricante' não foi encontrado na classe Produto")
            }
        }

        @Test
        @DisplayName("Verificar se o método 'setFabricante' valida o tamanho do fabricante")
        fun `verificar se o método 'setFabricante' valida o tamanho do fabricante`() {
            try {

                val produto = Produto()
                val clazz = Produto::class.java
                val method = clazz.getDeclaredMethod("setFabricante", String::class.java)
                method.invoke(produto, "A")

                val field = clazz.getDeclaredField("fabricante")
                field.isAccessible = true

                assertEquals(
                    "",
                    field.get(produto),
                    "O método 'setFabricante' não está validando o tamanho do fabricante corretamente. Valide se o fabricante é maior que 3 caracteres."
                )
            } catch (e: NoSuchMethodException) {
                fail("O método 'setFabricante' não foi encontrado na classe Produto")
            }
        }
    }
}