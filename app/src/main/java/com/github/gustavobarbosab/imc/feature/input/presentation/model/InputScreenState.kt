package com.github.gustavobarbosab.imc.feature.input.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.common.value

@Immutable
data class InputScreenState(
    val height: String,
    val heightFeedback: FieldFeedback?,
    val weight: String,
    val weightFeedback: FieldFeedback?,
) {
    val backgroundColor
        get() = arrayOf(
            0.4f to Color(0xFF1BC9B9),
            0.9f to Color(0xFF8DAFFF)
        )

    companion object {
        fun initialState() = InputScreenState(
            height = "",
            heightFeedback = null,
            weight = "",
            weightFeedback = null
        )
    }
}

sealed class FieldFeedback {
    data object Nothing : FieldFeedback()
    data class Error(val feedbackText: UiText) : FieldFeedback()
}

@Composable
fun FieldFeedback?.onFeedback(
    func: @Composable (String) -> Unit
): (@Composable () -> Unit)? = when (this) {
    is FieldFeedback.Error -> {
        {
            func(feedbackText.value)
        }
    }

    else -> null
}