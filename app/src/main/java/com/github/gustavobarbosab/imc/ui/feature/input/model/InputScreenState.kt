package com.github.gustavobarbosab.imc.ui.feature.input.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

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
    data class Error(
        @StringRes val feedbackResource: Int
    ) : FieldFeedback()
}