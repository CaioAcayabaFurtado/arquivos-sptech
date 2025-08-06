package sptech.projetoc2.service

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class CalculadoraCategoriaServiceTest {

    @Test
    fun getCategoriaPorPeso(){
        val pesoTeste = 49.99
        val calculadora = CalculadoraCategoriaService()
        val resultado =
            calculadora.getCategoriaPorPeso(pesoTeste)
        assertEquals("Mosca", resultado)
    }
}