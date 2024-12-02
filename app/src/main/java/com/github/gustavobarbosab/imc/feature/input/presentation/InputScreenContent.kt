package com.github.gustavobarbosab.imc.feature.input.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.github.gustavobarbosab.imc.R
import com.github.gustavobarbosab.imc.common.components.PrimaryButton
import com.github.gustavobarbosab.imc.feature.input.presentation.model.InputScreenState
import com.github.gustavobarbosab.imc.feature.input.presentation.model.onFeedback
import com.github.gustavobarbosab.imc.theme.BMITheme
import com.github.gustavobarbosab.imc.theme.spacing

@Composable
fun InputScreenContent(
    modifier: Modifier = Modifier,
    screenState: InputScreenState,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onClickToCalculate: () -> Unit
) {
    val gradient = Brush.linearGradient(
        0.4f to Color(0xFF1BC9B9),
        0.9f to Color(0xFF8DAFFF)
    )

    Column(
        modifier
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
                .padding(horizontal = MaterialTheme.spacing.extraMedium),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            suffix = { Text("kg") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            supportingText = screenState.weightFeedback.onFeedback { feedback ->
                Text(feedback)
            },
            value = screenState.weight,
            onValueChange = onWeightChanged,
            label = { Text("Digite seu peso") },
            placeholder = { Text("Ex: 89.3") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.extraMedium),
            suffix = { Text("cm") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Go
            ),
            supportingText = screenState.heightFeedback.onFeedback { feedback ->
                Text(feedback)
            },
            value = screenState.height,
            onValueChange = onHeightChanged,
            label = { Text("Digite sua altura") },
            placeholder = { Text("Ex: 180") }
        )

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.extraMedium,
                    end = MaterialTheme.spacing.extraMedium,
                    top = MaterialTheme.spacing.large
                ),
            onClick = onClickToCalculate
        ) {
            Text("Calcular")
        }
    }

}


@Preview(
    device = "id:pixel_9_pro",
    showBackground = true, showSystemUi = true
)
@Composable
private fun preview() {
    BMITheme {
        InputScreenContent(
            modifier = Modifier,
            onHeightChanged = {},
            onWeightChanged = {},
            onClickToCalculate = {},
            screenState = InputScreenState.initialState()
        )
    }
}
