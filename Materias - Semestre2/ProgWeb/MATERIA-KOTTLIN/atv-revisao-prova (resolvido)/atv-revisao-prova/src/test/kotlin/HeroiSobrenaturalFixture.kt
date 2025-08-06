class HeroiSobrenaturalFixture {
    companion object {

        fun umHeroiSobrenatural(): HeroiSobrenatural {
            val clazz = HeroiSobrenatural::class.java
            val heroiSobrenatural = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "Wanda Maximoff")
            }

            clazz.getDeclaredField("poderPrincipal").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "Feitiçaria")
            }

            clazz.getDeclaredField("nivelEnergia").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, 100)
            }

            clazz.getDeclaredField("poderSecundario").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "")
            }

            return heroiSobrenatural
        }

        fun umHeroiSobrenaturalComPoderSecundario(): HeroiSobrenatural {
            val clazz = HeroiSobrenatural::class.java
            val heroiSobrenatural = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "Wanda Maximoff")
            }

            clazz.getDeclaredField("poderPrincipal").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "Feitiçaria")
            }

            clazz.getDeclaredField("nivelEnergia").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, 100)
            }

            clazz.getDeclaredField("poderSecundario").let {
                it.isAccessible = true
                it.set(heroiSobrenatural, "Telecinese")
            }

            return heroiSobrenatural
        }
    }
}