package com.github.gustavobarbosab.example

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class CarroTest {

    private val tanqueDeCombustivelDuble = mockk<TanqueDeCombustivel>()
    private val motorDuble = mockk<Motor>()
    private val carro = Carro(motorDuble, tanqueDeCombustivelDuble)

    @Test
    fun `quando ligar o carro já ligado e tem combustível, o carro deve manter o motor ligado`() {
        // GIVEN
        every { motorDuble.ligado } returns true
        every { motorDuble.ligar() } just runs
        every { tanqueDeCombustivelDuble.temCombustivel() } returns true

        // WHEN
        val ligou = carro.ligar()

        // THEN
        Assert.assertTrue(ligou)
        verify {
            tanqueDeCombustivelDuble.temCombustivel()
        }
        verify(exactly = 0) {
            motorDuble.ligar()
        }
    }

    @Test
    fun `quando desligar o carro, deve desligar o motor`() {
        // GIVEN
        every { motorDuble.desligar() } just runs

        // WHEN
        carro.desligar()

        // THEN
        verify {
            motorDuble.desligar()
        }
    }

    @Test
    fun `quando ligar o carro desligado e com combustivel, deve ligar o motor`() {
        // GIVEN
        every { motorDuble.ligado } returns false
        every { motorDuble.ligar() } just runs
        every { tanqueDeCombustivelDuble.temCombustivel() } returns true

        // WHEN
        val ligou = carro.ligar()

        // THEN
        Assert.assertTrue(ligou)
        verify {
            tanqueDeCombustivelDuble.temCombustivel()
            motorDuble.ligar()
        }
    }

    @Test
    fun `quando ligar o carro sem combustivel, não deve ligar o carro`() {
        // GIVEN
        every { tanqueDeCombustivelDuble.temCombustivel() } returns false

        // WHEN
        val ligou = carro.ligar()

        // THEN
        Assert.assertFalse(ligou)
        verify {
            tanqueDeCombustivelDuble.temCombustivel()
        }
    }

    @Test
    fun `quando acelerar o carro ligado, deve consumir combustivel e acelerar`() {
        // GIVEN
        every { motorDuble.ligado } returns true
        every { tanqueDeCombustivelDuble.consumirCombustivel(any()) } returns 0

        // WHEN
        val acelerou = carro.acelerar()

        // THEN
        Assert.assertTrue(acelerou)
        verify {
            tanqueDeCombustivelDuble.consumirCombustivel(quantidade = 1)
        }
    }

    @Test
    fun `quando acelerar o carro desligado, não deve consumir combustivel e não irá acelerar`() {
        // GIVEN
        every { motorDuble.ligado } returns false

        // WHEN
        val acelerou = carro.acelerar()

        // THEN
        Assert.assertFalse(acelerou)
        verify(exactly = 0) {
            tanqueDeCombustivelDuble.consumirCombustivel(quantidade = 1)
        }
    }
}