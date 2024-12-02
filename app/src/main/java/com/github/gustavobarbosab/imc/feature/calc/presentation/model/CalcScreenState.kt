package com.github.gustavobarbosab.imc.feature.calc.presentation.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText

data class CalcScreenState(
    val bmi: UiText,
    val message: UiText,
    private val backgroundColorList: List<Pair<Float, Color>>,
    val showAbout: Boolean,
) {
    val backgroundColor
        get() = backgroundColorList.toTypedArray()

    companion object {
        fun initialState() = CalcScreenState(
            bmi = UiText.TextString("--"),
            message = UiText.TextString("Loading..."),
            backgroundColorList = listOf(

            ),
            showAbout = false
        )
    }
}
