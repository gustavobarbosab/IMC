package com.github.gustavobarbosab.imc.feature.input.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun InputScreen(
    viewModel: InputScreenViewModel = InputScreenViewModel()
) {
    val screenState by viewModel.state.collectAsState()

    InputScreenContent(
        screenState = screenState,
        onWeightChanged = viewModel::onWeightChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onClickToCalculate = viewModel::onClickToCalculate
    )
}
