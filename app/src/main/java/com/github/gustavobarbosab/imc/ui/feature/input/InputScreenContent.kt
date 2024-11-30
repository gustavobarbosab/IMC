package com.github.gustavobarbosab.imc.ui.feature.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.imc.R
import com.github.gustavobarbosab.imc.ui.feature.input.model.InputScreenState

@Composable
fun InputScreenContent(
    modifier: Modifier = Modifier,
    screenState: InputScreenState,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onClickToCalculate: () -> Unit
) {
    val gradient = Brush.linearGradient(
        0.4f to Color(0xFF2DFFD8),
        0.9f to Color(0xFF8DAFFF)
    )
    Scaffold(modifier) { padding ->
        Column(
            Modifier
                .padding(padding)
                .background(gradient)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxHeight(0.4f)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "App logo"
                )
            }

            Spacer(
                modifier = Modifier.fillMaxHeight(0.3f)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = screenState.weight,
                onValueChange = onWeightChanged,
                label = { Text("Digite seu peso") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = screenState.height,
                onValueChange = onHeightChanged,
                label = { Text("Digite sua altura") }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 32.dp
                    ),
                contentPadding = PaddingValues(vertical = 16.dp),
                onClick = onClickToCalculate
            ) {
                Text("Calcular")
            }
        }
    }
}


@Preview(device = "id:pixel_9_pro")
@Composable
private fun preview() {
    InputScreenContent(
        modifier = Modifier,
        onHeightChanged = {},
        onWeightChanged = {},
        onClickToCalculate = {},
        screenState = InputScreenState.initialState()
    )
}
