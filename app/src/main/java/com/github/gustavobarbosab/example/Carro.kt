package com.github.gustavobarbosab.example

class Carro(
    private val motor: Motor,
    private val tanqueDeCombustivel: TanqueDeCombustivel
) {
    fun ligar(): Boolean {
        if (!tanqueDeCombustivel.temCombustivel()) {
            println("Carro está sem combustível!")
            return false
        }

        if (!motor.ligado) {
            motor.ligar()
        }
        return true
    }

    fun acelerar(): Boolean {
        if (!motor.ligado) {
            println("Seu carro está desligado, ligue para andar!")
            return false
        }

        tanqueDeCombustivel.consumirCombustivel(quantidade = 1)
        return true
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

    private var nivelDeCombustivel = capacidadeEmLitros

    fun consumirCombustivel(quantidade: Int): Int {
        nivelDeCombustivel = maxOf(nivelDeCombustivel - quantidade, 0)
        return nivelDeCombustivel
    }

    fun temCombustivel(): Boolean {
        return nivelDeCombustivel > 0
    }
}


fun main() {
    val motor = Motor()
    val tanqueDeCombustivel = TanqueDeCombustivel(capacidadeEmLitros = 50)
    val carro = Carro(motor, tanqueDeCombustivel)
    println("Acelerou? ${carro.acelerar()}")
}
