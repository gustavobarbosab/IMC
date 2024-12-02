package com.github.gustavobarbosab.imc.feature.input.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object InputScreenRoute

fun NavGraphBuilder.inputScreenRoute(navController: NavController) {
    composable<InputScreenRoute> {
        InputScreen(navController)
    }
}