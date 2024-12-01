package com.github.gustavobarbosab.imc.feature.calc.presentation.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText

sealed class BMIWeightState(
    val color: Array<Pair<Float, Color>>,
    val description: UiText
) {
    data object Underweight : BMIWeightState(
        arrayOf(
            0.4f to Color(0xff2E86CE),
            0.9f to Color(0xff54C8EB),
        ),
        UiText.TextString("")
    )

    data object Normal : BMIWeightState(
        arrayOf(
            0.4f to Color(0xff2ECE81),
            0.9f to Color(0xff8BEB54),
        ),
        UiText.TextString("")
    )

    data object Overweight : BMIWeightState(
        arrayOf(
            0.4f to Color(0xffCEC12E),
            0.9f to Color(0xffEBE354),
        ),
        UiText.TextString("")
    )

    data object Obese : BMIWeightState(
        arrayOf(
            0.4f to Color(0xffCE2E2E),
            0.9f to Color(0xffEB5454),
        ),
        UiText.TextString("")
    )
}