package com.github.gustavobarbosab.imc.feature.input.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.gustavobarbosab.imc.feature.calc.presentation.CalcScreenRoute

@Composable
fun InputScreen(navController: NavController) {
    val viewModel: InputScreenViewModel = viewModel()
    val screenState by viewModel.state.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(true) {
        viewModel.viewAction.collect { viewAction ->
            when (viewAction) {
                InputScreenViewModel.ViewActions.CloseKeyboard -> keyboardController?.hide()
                is InputScreenViewModel.ViewActions.RedirectToCalc -> navController.navigate(
                    CalcScreenRoute(viewAction.weight, viewAction.height)
                )
            }
        }
    }

    InputScreenContent(
        screenState = screenState,
        onWeightChanged = viewModel::onWeightChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onClickToCalculate = viewModel::onClickToCalculate
    )
}
