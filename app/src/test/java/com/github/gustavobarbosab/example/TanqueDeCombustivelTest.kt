package com.github.gustavobarbosab.example

import org.junit.Assert
import org.junit.Test

class TanqueDeCombustivelTest {

    @Test
    fun `quando consumir menos combustível que o disponível, deve dizer que há combustível`() {
        // GIVEN
        val tanqueDeCombustivel = TanqueDeCombustivel(capacidadeEmLitros = 60)

        // WHEN
        val novoNivel = tanqueDeCombustivel.consumirCombustivel(quantidade = 40)

        // THEN
        val esperado = 20
        Assert.assertEquals(esperado, novoNivel)
        Assert.assertTrue(tanqueDeCombustivel.temCombustivel())
    }

    @Test
    fun `quando consumir o combustivel e não tiver mais disponível, deve dizer que não há combustível`() {
        // GIVEN
        val tanqueDeCombustivel = TanqueDeCombustivel(capacidadeEmLitros = 0)

        // WHEN
        val novoNivel = tanqueDeCombustivel.consumirCombustivel(quantidade = 40)

        // THEN
        val esperado = 0
        Assert.assertEquals(esperado, novoNivel)
        Assert.assertFalse(tanqueDeCombustivel.temCombustivel())
    }

    @Test
    fun `quando consumir mais combustivel que o disponível, deve zerar o nivel de combustivel`() {
        // GIVEN
        val tanqueDeCombustivel = TanqueDeCombustivel(capacidadeEmLitros = 30)

        // WHEN
        val novoNivel = tanqueDeCombustivel.consumirCombustivel(quantidade = 40)

        // THEN
        val esperado = 0
        Assert.assertEquals(esperado, novoNivel)
        Assert.assertFalse(tanqueDeCombustivel.temCombustivel())
    }
}