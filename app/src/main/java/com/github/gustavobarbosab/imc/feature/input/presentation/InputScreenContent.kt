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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.github.gustavobarbosab.imc.R
import com.github.gustavobarbosab.imc.common.BMITextFieldDefaults
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
    val gradient = remember {
        Brush.linearGradient(colorStops = screenState.backgroundColor)
    }

    Scaffold { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(gradient),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.fillMaxHeight(0.4f)
            ) {
                Image(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = stringResource(R.string.input_screen_logo_content_description)
                )
            }

            Spacer(
                modifier = Modifier.fillMaxHeight(0.3f)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraMedium),
                colors = BMITextFieldDefaults.colors,
                suffix = { Text(stringResource(R.string.input_screen_weight_field_suffix)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                supportingText = screenState.weightFeedback.onFeedback { feedback ->
                    Text(feedback)
                },
                value = screenState.weight,
                onValueChange = onWeightChanged,
                label = { Text(stringResource(R.string.input_screen_weight_field_label)) },
                placeholder = { Text(stringResource(R.string.input_screen_weight_field_hint)) }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.extraMedium),
                suffix = { Text(stringResource(R.string.input_screen_height_field_suffix)) },
                colors = BMITextFieldDefaults.colors,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Go
                ),
                supportingText = screenState.heightFeedback.onFeedback { feedback ->
                    Text(feedback)
                },
                value = screenState.height,
                onValueChange = onHeightChanged,
                label = { Text(stringResource(R.string.input_screen_height_field_label)) },
                placeholder = { Text(stringResource(R.string.input_screen_height_field_hint)) }
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
                Text(stringResource(R.string.input_screen_height_button_label))
            }
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
