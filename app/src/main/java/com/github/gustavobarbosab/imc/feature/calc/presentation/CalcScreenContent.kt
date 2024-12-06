package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.gustavobarbosab.imc.R
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.common.components.SecondaryButton
import com.github.gustavobarbosab.imc.common.value
import com.github.gustavobarbosab.imc.feature.about.AboutBMIBottomSheet
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenBMIBackgroundColor
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenState
import com.github.gustavobarbosab.imc.theme.BMITheme
import com.github.gustavobarbosab.imc.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcScreenContent(
    modifier: Modifier = Modifier,
    screenState: CalcScreenState,
    onClickToLearnMore: () -> Unit,
    onDismissAboutModal: () -> Unit,
    onBackPressed: () -> Unit
) {
    val gradient = remember { Brush.linearGradient(colorStops = screenState.backgroundColor) }

    Box(Modifier.background(gradient)) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = MaterialTheme.spacing.extraMedium),
                            text = stringResource(R.string.calculator_toolbar),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                Icons.Outlined.Close,
                                contentDescription = stringResource(R.string.calculator_toolbar_back_button_content_description)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = MaterialTheme.spacing.extraMedium),
                verticalArrangement = Arrangement.spacedBy(
                    MaterialTheme.spacing.medium,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.weight(0.7f),
                    verticalArrangement = Arrangement.spacedBy(
                        MaterialTheme.spacing.medium,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        screenState.bmi.value,
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.White
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
                            .padding(vertical = MaterialTheme.spacing.medium),
                        onClick = onClickToLearnMore,
                    ) {
                        Text(stringResource(R.string.calculator_button_label))
                    }
                }

                AboutBMIBottomSheet(
                    onDismiss = onDismissAboutModal,
                    visible = screenState.showAbout
                )
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
                bmi = UiText.TextString("43.9"),
                message = UiText.TextString("Você está com obesidade nível 2..."),
                backgroundColorList = CalcScreenBMIBackgroundColor.Overweight.color,
                showAbout = false
            ),
            onClickToLearnMore = {},
            onDismissAboutModal = {},
            onBackPressed = {}
        )
    }
}