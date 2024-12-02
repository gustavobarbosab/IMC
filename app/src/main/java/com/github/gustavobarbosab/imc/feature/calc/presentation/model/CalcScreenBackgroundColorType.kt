package com.github.gustavobarbosab.imc.feature.calc.presentation.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.feature.calc.data.entity.BMIType

sealed class CalcScreenBMIBackgroundColor(
    val color: List<Pair<Float, Color>>,
) {
    data object Underweight : CalcScreenBMIBackgroundColor(
        listOf(
            0.4f to Color(0xff2E86CE),
            0.9f to Color(0xff54C8EB),
        )
    )

    data object Normal : CalcScreenBMIBackgroundColor(
        listOf(
            0.4f to Color(0xff2ECE81),
            0.9f to Color(0xff8BEB54),
        )
    )

    data object Overweight : CalcScreenBMIBackgroundColor(
        listOf(
            0.4f to Color(0xffCEC12E),
            0.9f to Color(0xffEBE354),
        )
    )

    data object Obese : CalcScreenBMIBackgroundColor(
        listOf(
            0.4f to Color(0xffCE2E2E),
            0.9f to Color(0xffEB5454),
        )
    )

    data object Default : CalcScreenBMIBackgroundColor(
        listOf(
            0.4f to Color.Gray,
            0.8f to Color.LightGray
        )
    )
}

fun BMIType.mapToBackgroundColor() = when (this) {
    is BMIType.Underweight -> CalcScreenBMIBackgroundColor.Underweight
    is BMIType.Normal -> CalcScreenBMIBackgroundColor.Normal
    is BMIType.Overweight -> CalcScreenBMIBackgroundColor.Overweight
    is BMIType.Obese -> CalcScreenBMIBackgroundColor.Obese
}
