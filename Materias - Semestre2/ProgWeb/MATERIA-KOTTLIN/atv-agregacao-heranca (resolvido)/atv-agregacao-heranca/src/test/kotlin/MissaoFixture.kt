class MissaoFixture {
    companion object {

        fun missoes(): List<Missao> {
            return mutableListOf(missaoFacil(), missaoMedia(), missaoDificil())
        }

        fun umaMissao(): Missao {
            val clazz = Missao::class.java
            val missao = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(missao, "Missão Exploratória")
            }

            clazz.getDeclaredField("descricao").let {
                it.isAccessible = true
                it.set(missao, "Explorar a floresta encantada e descobrir novos segredos.")
            }

            clazz.getDeclaredField("nivelDificuldade").let {
                it.isAccessible = true
                it.set(missao, 3)
            }

            return missao
        }

        fun missaoFacil(): Missao {
            val clazz = Missao::class.java
            val missao = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(missao, "Missão de Treinamento")
            }

            clazz.getDeclaredField("descricao").let {
                it.isAccessible = true
                it.set(missao, "Completar o treinamento básico de combate.")
            }

            clazz.getDeclaredField("nivelDificuldade").let {
                it.isAccessible = true
                it.set(missao, 1)
            }

            return missao
        }

        fun missaoMedia(): Missao {
            val clazz = Missao::class.java
            val missao = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(missao, "Missão de Resgate")
            }

            clazz.getDeclaredField("descricao").let {
                it.isAccessible = true
                it.set(missao, "Resgatar o prisioneiro das masmorras do castelo.")
            }

            clazz.getDeclaredField("nivelDificuldade").let {
                it.isAccessible = true
                it.set(missao, 5)
            }

            return missao
        }

        fun missaoDificil(): Missao {
            val clazz = Missao::class.java
            val missao = clazz.getDeclaredConstructor().newInstance()

            clazz.getDeclaredField("nome").let {
                it.isAccessible = true
                it.set(missao, "Missão Épica")
            }

            clazz.getDeclaredField("descricao").let {
                it.isAccessible = true
                it.set(missao, "Derrotar o dragão ancião que aterroriza o reino.")
            }

            clazz.getDeclaredField("nivelDificuldade").let {
                it.isAccessible = true
                it.set(missao, 10)
            }

            return missao
        }
    }
}