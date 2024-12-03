package com.github.gustavobarbosab.imc.feature.about.model

import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.theme.JordyBlue
import com.github.gustavobarbosab.imc.theme.KiwiGreen
import com.github.gustavobarbosab.imc.theme.MinionYellow
import com.github.gustavobarbosab.imc.theme.PersianRed

data class AboutBMIScreenState(
    val types: List<AboutBMIType> = AboutBMIType.entries
) {
    enum class AboutBMIType(
        val color: Color,
        val description: UiText
    ) {
        UNDERWEIGHT(
            Color.JordyBlue,
            UiText.TextString("Menor de 18,5: Abaixo do peso")
        ),
        NORMAL(
            Color.KiwiGreen,
            UiText.TextString("Entre 18,5 - 24,9: Peso normal")
        ),
        OVERWEIGHT(
            Color.MinionYellow,
            UiText.TextString("Entre 25,0 - 29,9: Excesso de peso")
        ),
        OBESITY(
            Color.PersianRed,
            UiText.TextString("Maior que 30: Obesidade")
        )
    }
}