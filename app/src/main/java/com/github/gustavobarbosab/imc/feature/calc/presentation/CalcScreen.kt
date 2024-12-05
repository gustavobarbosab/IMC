package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.gustavobarbosab.imc.theme.BMITheme

@Composable
fun CalcScreen(navController: NavController) {
    val viewModel = viewModel<CalcScreenViewModel>()
    val screenState by viewModel.state.collectAsState()

    CalcScreenContent(
        screenState = screenState,
        onClickToLearnMore = { viewModel.showAboutBottomSheet() },
        onDismissAboutModal = { viewModel.dismissAboutBottomSheet() },
        onBackPressed = { navController.navigateUp() }
    )
}


@Preview
@Composable
private fun CalcScreenPreview() {
    val navController = rememberNavController()
    BMITheme {
        CalcScreen(navController)
    }
}