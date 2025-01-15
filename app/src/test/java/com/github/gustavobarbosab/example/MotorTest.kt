package com.github.gustavobarbosab.example

import org.junit.Assert
import org.junit.Test

class MotorTest {

    @Test
    fun `quando ligar o motor, deverá mudar o estado para ligado`() {
        // GIVEN
        val motor = Motor()

        // WHEN
        motor.ligar()
        val ligou = motor.ligado

        // THEN
        Assert.assertTrue(ligou)
    }

    @Test
    fun `quando desligar o motor, deverá mudar o estado para desligado`() {
        // GIVEN
        val motor = Motor()

        // WHEN
        motor.desligar()
        val desligou = motor.ligado

        // THEN
        Assert.assertFalse(desligou)
    }
}