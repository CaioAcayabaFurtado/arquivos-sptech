class ProdutoFixture {

    companion object {
        fun umProduto(): Produto {

            val clazz = Produto::class.java
            val fieldNome = clazz.getDeclaredField("nome")
            fieldNome.isAccessible = true
            val fieldCategoria = clazz.getDeclaredField("categoria")
            fieldCategoria.isAccessible = true
            val fieldPreco = clazz.getDeclaredField("preco")
            fieldPreco.isAccessible = true
            val fieldFabricante = clazz.getDeclaredField("fabricante")
            fieldFabricante.isAccessible = true

            val produto = Produto()
            fieldNome.set(produto, "Produto 1")
            fieldCategoria.set(produto, "Categoria 1")
            fieldPreco.set(produto, 10.0)
            fieldFabricante.set(produto, "Fabricante 1")

            return produto
        }
    }
}