package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.imc.common.components.SecondaryButton
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.common.value
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.BMIWeightState
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenState
import com.github.gustavobarbosab.imc.theme.BMITheme
import com.github.gustavobarbosab.imc.theme.spacing

@Composable
fun CalcScreenContent(
    modifier: Modifier = Modifier,
    screenState: CalcScreenState,
    onClickToLearnMore: () -> Unit
) {
    Scaffold(
        modifier
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colorStops = screenState.backgroundColor
                    )
                )
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    screenState.bmi,
                    style = MaterialTheme.typography.displayLarge
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(vertical = MaterialTheme.spacing.medium),
                    thickness = 1.dp,
                    color = Color.White
                )

                Text(
                    screenState.message.value,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Box(
                Modifier.weight(0.15f),
                contentAlignment = Alignment.TopCenter
            ) {
                SecondaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    onClick = onClickToLearnMore,
                ) {
                    Text("Saiba mais")
                }
            }
        }
    }
}

@Preview
@Composable
private fun preview() {
    BMITheme {
        CalcScreenContent(
            modifier = Modifier,
            screenState = CalcScreenState(
                bmi = "43.9",
                message = UiText.TextString("Você está com obesidade nível 2..."),
                backgroundColor = BMIWeightState.Overweight.color
            ),
            onClickToLearnMore = {}
        )
    }
}