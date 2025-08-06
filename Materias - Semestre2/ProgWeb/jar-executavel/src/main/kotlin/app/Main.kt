package app

import dominio.Carro
import repositorio.CarroRepositorio

open class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val repositorio = CarroRepositorio()
            repositorio.configurar()
            repositorio.criarTabela()
            while (true) {
                println(
                    """
            1 - Inserir
            2 - Buscar por ID
            3 - Vender por ID
            4 - Sair
        """.trimIndent()
                )
                println("Digite uma opção: ")
                val opcao = readln().toInt()
                when (opcao) {
                    1 -> {
                        val novoCarro = Carro()
                        println("Digite o nome do novo carro: ")
                        novoCarro.setNome(readln())
                        repositorio.inserir(novoCarro)
                    }

                    2 -> {
                        println("Digite o ID do carro:")
                        val id = readln().toInt()
                        if (repositorio.existePorId(id)) {
                            val carroEncontrado = repositorio.buscarPorId(id)
                            println(
                                """
                    Nome: ${carroEncontrado.nome}
                    Vendido: ${carroEncontrado.vendido}
                """.trimIndent()
                            )
                        } else {
                            println("Id não encontrado")
                        }
                    }

                    3 -> {
                        println("Digite o ID do carro que deseja vender:")
                        val id = readln().toInt()
                        if (repositorio.existePorId(id)) {
                            val vendido = repositorio.venderPorId(id)
                            if (vendido) {


                                println(
                                    """
                    Carro vendido com sucesso!!!
                """.trimIndent()
                                )
                            } else {
                                println("Deu ruim")
                            }
                        } else {
                            println("Id não existe")
                        }
                    }

                    4 -> break
                    else -> println("Opção inválida")
                }
            }

        }
    }
}