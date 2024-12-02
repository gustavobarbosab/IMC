package com.github.gustavobarbosab.imc.feature.calc.data.entity

import androidx.annotation.StringRes
import com.github.gustavobarbosab.imc.R

sealed class BMIType(
    open val bmiValue: String,
    @StringRes val feedback: Int
) {
    data class Underweight(
        override val bmiValue: String
    ) : BMIType(
        bmiValue,
        R.string.calculator_underweight
    )

    data class Normal(
        override val bmiValue: String
    ) : BMIType(
        bmiValue,
        R.string.calculator_normal
    )

    data class Overweight(
        override val bmiValue: String
    ) : BMIType(
        bmiValue,
        R.string.calculator_overweight
    )

    data class Obese(
        override val bmiValue: String
    ) : BMIType(
        bmiValue,
        R.string.calculator_obese
    )
}
