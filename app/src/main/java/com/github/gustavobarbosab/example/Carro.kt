package com.github.gustavobarbosab.example

class VoceNaoTemCombustivelException(override val message: String? = null) : Throwable(message)

class CarroDesligadoException(override val message: String? = null) : Throwable(message)

class Carro {
    private val motor = Motor()
    private val tanqueDeCombustivel = TanqueDeCombustivel(capacidadeEmLitros = 60)

    fun ligar() {
        if (!tanqueDeCombustivel.temCombustivel()) {
            throw VoceNaoTemCombustivelException("Carro está sem combustível!")
        }

        motor.ligar()
    }

    fun andar() {
        if (!motor.ligado) {
            throw CarroDesligadoException("Seu carro está desligado, ligue para andar!")
        }

        tanqueDeCombustivel.consumirCombustivel(quantidade = 1)
    }

    fun desligar() {
        motor.desligar()
    }
}

class Motor {
    var ligado: Boolean = false
        private set

    fun ligar() {
        ligado = true
    }

    fun desligar() {
        ligado = false
    }

}

class TanqueDeCombustivel(val capacidadeEmLitros: Int) {

    var nivelDeCombustivel = capacidadeEmLitros
        private set

    fun consumirCombustivel(quantidade: Int) {
        nivelDeCombustivel = minOf(nivelDeCombustivel - quantidade, 0)
    }

    fun temCombustivel(): Boolean {
        return nivelDeCombustivel > 0
    }
}


fun main() {
    val carro = Carro()

    // simulando exceção
    carro.andar()
}