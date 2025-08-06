class FilmeFixture {
    companion object {
        fun umFilme(): Filme {

            val clazz = Filme::class.java
            val filme = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("id").let {
                it.isAccessible = true
                it.set(filme, 1)
            }

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(filme, "Shrek")
            }

            clazz.getDeclaredField("genero").let {
                it.isAccessible = true
                it.set(filme, "Comédia")
            }

            clazz.getDeclaredField("anoLancamento").let {
                it.isAccessible = true
                it.set(filme, 2001)
            }

            clazz.getDeclaredField("duracao").let {
                it.isAccessible = true
                it.set(filme, 90)
            }

            clazz.getDeclaredField("alugado").let {
                it.isAccessible = true
                it.set(filme, false)
            }

            return filme
        }
    }
}