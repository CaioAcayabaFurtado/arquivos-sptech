package app

import dominio.Carro
import repositorio.CarroRepositorio

open class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {


            val repositorio = CarroRepositorio()
            repositorio.configurar() // inicializando jdbcTemplate
            repositorio.criarTabela() // usando jdbcTemplate para criar tabela

            while (true) {
                println(
                    """
                    1 - Inserir
                    2 - Buscar por id
                    3 - Vender por id
                    4 - Sair
                    """.trimIndent()
                )
                print("Digite uma opção: ")
                val opcao = readln().toInt()

                when (opcao) {
                    1 -> {
                        val novoCarro = Carro()
                        print("Digite o nome do novo carro: ")
                        novoCarro.setNome(readln())

                        repositorio.inserir(novoCarro)
                    }

                    2 -> {
                        print("Digite o id do carro: ")
                        val id = readln().toInt()

                        if (repositorio.existePorId(id)) {
                            val carroEncontrado = repositorio.buscarPorId(id)

                            println(
                                """
                                -----------------------------
                                Nome: ${carroEncontrado.nome}
                                Vendido: ${if (carroEncontrado.vendido) "Sim" else "Não"}
                                -----------------------------
                                """.trimIndent()
                            )
                        } else {
                            println("Id não existe!")
                        }
                    }

                    3 -> {
                        print("Qual id você deseja vender: ")
                        val id = readln().toInt()
// OPÇÃO 1 - Verificar se existe no banco para depois atualizar
//                if (repositorio.existePorId(id)) {
//                    val vendido = repositorio.venderPorId(id)
//
//                    if (vendido) {
//                        println("Carro vendido com sucesso!")
//                    } else {
//                        println("Deu ruim!")
//                    }
//                } else {
//                    println("Id não existe")
//                }

                        // OPÇÃO 2 - Usar o próprio update para verificar se existe
                        val vendido = repositorio.venderPorId(id)
                        if (vendido) { // se não existe id, "vendido" é false e cai no else
                            println("Vendido com sucesso!")
                        } else {
                            println("Carro não existe!")
                        }

                    }

                    4 -> break // parar o loop
                    else -> println("Opção inválida!") // caso digite uma opção inexistente
                }
            }
        }
    }
}