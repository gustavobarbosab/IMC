package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CalcScreenRoute(
    val weight: Float,
    val height: Int
)

fun NavGraphBuilder.calcScreenRoute(navController: NavController) {
    composable<CalcScreenRoute> {
        CalcScreen(navController)
    }
}