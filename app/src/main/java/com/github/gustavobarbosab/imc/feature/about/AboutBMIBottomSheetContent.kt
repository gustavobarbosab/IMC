package com.github.gustavobarbosab.imc.feature.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.gustavobarbosab.imc.common.components.PrimaryButton
import com.github.gustavobarbosab.imc.common.value
import com.github.gustavobarbosab.imc.feature.about.model.AboutBMIScreenState
import com.github.gustavobarbosab.imc.theme.BMITheme
import com.github.gustavobarbosab.imc.theme.PhilippineGray
import com.github.gustavobarbosab.imc.theme.spacing

@Composable
fun AboutBMIBottomSheetContent(
    modifier: Modifier = Modifier,
    screenState: AboutBMIScreenState,
    onGotItClicked: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Índice de massa corporal",
            style = MaterialTheme.typography.titleSmall
        )

        LazyColumn {
            items(
                items = screenState.types,
                key = { item -> item.name }
            ) { item ->
                BMIItem(item)
            }
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.large),
            onClick = onGotItClicked
        ) {
            Text("Entendi")
        }
    }
}

@Composable
private fun BMIItem(item: AboutBMIScreenState.AboutBMIType) {
    Row(
        Modifier.padding(
            vertical = MaterialTheme.spacing.medium
        ),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Box(
            Modifier
                .size(MaterialTheme.spacing.medium)
                .background(item.color, MaterialTheme.shapes.small)
        )

        Text(
            modifier = Modifier.weight(1f),
            text = item.description.value,
            style = MaterialTheme.typography.bodySmall,
            color = Color.PhilippineGray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BottomSheetPreview() {
    val bottomSheetState = rememberModalBottomSheetState()
    BMITheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AboutBMIBottomSheetContent(
                Modifier,
                AboutBMIScreenState(),
                {}
            )
        }
    }
}