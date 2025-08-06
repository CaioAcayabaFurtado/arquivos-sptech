class HabilidadeFixture {
    companion object {
        fun umaHabilidade(): Habilidade {
            val clazz = Habilidade::class.java
            val habilidade = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(habilidade, "Ataque")
            }

            clazz.getDeclaredField("poder").let {
                it.isAccessible = true
                it.set(habilidade, 10)
            }

            return habilidade
        }
    }
}