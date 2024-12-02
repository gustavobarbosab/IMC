package com.github.gustavobarbosab.imc.feature.calc.presentation.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText

data class CalcScreenState(
    val bmi: String,
    val message: UiText,
    val showAbout: Boolean,
    private val _backgroundColor: List<Pair<Float, Color>>
) {
    val background: Array<Pair<Float, Color>>
        get() = _backgroundColor.toTypedArray()

    companion object {
        fun initialState() = CalcScreenState(
            bmi = "",
            message = UiText.TextString(""),
            showAbout = false,
            _backgroundColor = listOf(
                0.4f to Color.Gray,
                0.8f to Color.LightGray,
            )
        )
    }
}
