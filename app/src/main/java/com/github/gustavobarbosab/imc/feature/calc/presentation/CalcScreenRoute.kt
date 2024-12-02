package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CalcScreenRoute

fun NavGraphBuilder.calcScreenRoute(navController: NavController) {
    composable<CalcScreenRoute> {
        CalcScreen()
    }
}