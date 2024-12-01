package com.github.gustavobarbosab.imc.feature.calc.presentation.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText

class CalcScreenState(
    val bmi: String,
    val message: UiText,
    val backgroundColor: Array<Pair<Float, Color>>
) {
    companion object {
        fun initialState() = CalcScreenState(
            bmi = "",
            message = UiText.TextString(""),
            backgroundColor = arrayOf(
                0.4f to Color.Gray,
                0.8f to Color.LightGray,
            )
        )
    }
}
