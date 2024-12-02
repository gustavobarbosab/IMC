package com.github.gustavobarbosab.imc.feature.calc.data.usecase

import com.github.gustavobarbosab.imc.feature.calc.data.entity.BMIType
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCaseImpl
import java.math.RoundingMode

interface BMICalculatorUseCase {

    fun calculate(weight: Float, height: Int): Result

    sealed class Result {
        data object InvalidData : Result()
        data class CalculatedData(val bmi: BMIType) : Result()
    }
}

class BMICalculatorUseCaseImpl(
    private val validatorUseCase: BMIDataValidatorUseCase = BMIDataValidatorUseCaseImpl()
) : BMICalculatorUseCase {

    override fun calculate(
        weight: Float,
        height: Int
    ): BMICalculatorUseCase.Result {
        val dataValidation = validatorUseCase.validate(
            weight.toString(),
            height.toString()
        )

        if (dataValidation !is BMIDataValidatorUseCase.ValidationResult.ValidFields) {
            return BMICalculatorUseCase.Result.InvalidData
        }

        val heightInMeter: Float = height / 100f
        val calculatedBMI = (weight / (heightInMeter * heightInMeter))
        val bigDecimalBMI = calculatedBMI.toBigDecimal().setScale(1, RoundingMode.HALF_DOWN)
        val formattedBMI = bigDecimalBMI.toString()

        val bmiType = when (calculatedBMI) {
            in Float.MIN_VALUE..<18.5f -> BMIType.Underweight(formattedBMI)
            in 18.5f..24.9f -> BMIType.Normal(formattedBMI)
            in 25.0f..29.9f -> BMIType.Overweight(formattedBMI)
            else -> BMIType.Obese(formattedBMI)
        }

        return BMICalculatorUseCase.Result.CalculatedData(bmiType)
    }
}
