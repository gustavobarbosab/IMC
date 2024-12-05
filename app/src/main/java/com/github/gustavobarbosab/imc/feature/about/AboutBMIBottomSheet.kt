package com.github.gustavobarbosab.imc.feature.about

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBMIBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    visible: Boolean
) {
    if (!visible) {
        return
    }

    val bottomSheetState = rememberModalBottomSheetState()
    val viewModel: AboutBMIViewModel = viewModel()
    val screenState by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        scrimColor = Color.Black.copy(0.6f),
        containerColor = Color.White,
        content = {
            AboutBMIBottomSheetContent(
                screenState = screenState,
                onGotItClicked = {
                    scope.launch {
                        bottomSheetState.hide()
                        onDismiss()
                    }
                }
            )
        }
    )
}
