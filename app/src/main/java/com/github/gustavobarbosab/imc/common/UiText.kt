package com.github.gustavobarbosab.imc.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class TextString(val value: String) : UiText()
    data class TextResource(@StringRes val value: Int) : UiText()
}

val UiText.value: String
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        is UiText.TextResource -> stringResource(this.value)
        is UiText.TextString -> this.value
    }