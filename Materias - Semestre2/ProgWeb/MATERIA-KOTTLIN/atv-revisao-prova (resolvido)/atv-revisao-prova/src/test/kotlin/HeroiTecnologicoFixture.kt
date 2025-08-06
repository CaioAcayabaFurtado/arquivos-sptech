class HeroiTecnologicoFixture {
    companion object {

        fun umHeroiSemGadget(): HeroiTecnologico {
            val clazz = HeroiTecnologico::class.java
            val heroiTecnologico = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "Tony Stark")
            }

            clazz.getDeclaredField("poderPrincipal").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "Armadura de Ferro")
            }

            clazz.getDeclaredField("nivelEnergia").let {
                it.isAccessible = true
                it.set(heroiTecnologico, 100)
            }

            clazz.getDeclaredField("gadgetAtual").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "")
            }

            return heroiTecnologico
        }

        fun umHeroiCompleto(): HeroiTecnologico {
            val clazz = HeroiTecnologico::class.java
            val heroiTecnologico = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "Tony Stark")
            }

            clazz.getDeclaredField("poderPrincipal").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "Armadura de Ferro")
            }

            clazz.getDeclaredField("nivelEnergia").let {
                it.isAccessible = true
                it.set(heroiTecnologico, 100)
            }

            clazz.getDeclaredField("gadgetAtual").let {
                it.isAccessible = true
                it.set(heroiTecnologico, "Reator Arc")
            }

            return heroiTecnologico
        }
    }
}