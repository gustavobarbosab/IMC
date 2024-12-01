package com.github.gustavobarbosab.imc.feature.input.presentation.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.github.gustavobarbosab.imc.common.UiText

@Immutable
data class InputScreenState(
    val height: String,
    val heightError: FieldFeedback?,
    val weight: String,
    val weightError: FieldFeedback?,
) {
    companion object {
        fun initialState() = InputScreenState(
            height = "",
            heightError = null,
            weight = "",
            weightError = null
        )
    }
}

sealed class FieldFeedback {
    data object Empty : FieldFeedback()
    data class Error(val feedbackText: UiText) : FieldFeedback()
}