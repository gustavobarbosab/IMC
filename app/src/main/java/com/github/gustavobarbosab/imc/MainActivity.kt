package com.github.gustavobarbosab.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.gustavobarbosab.imc.feature.calc.presentation.calcScreenRoute
import com.github.gustavobarbosab.imc.feature.input.presentation.InputScreenRoute
import com.github.gustavobarbosab.imc.feature.input.presentation.inputScreenRoute
import com.github.gustavobarbosab.imc.theme.BMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = InputScreenRoute
                    ) {
                        inputScreenRoute(navController)
                        calcScreenRoute(navController)
                    }
                }
            }
        }
    }
}
