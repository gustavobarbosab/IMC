package com.github.gustavobarbosab.imc.ui.feature.input

import androidx.lifecycle.ViewModel
import com.github.gustavobarbosab.imc.ui.feature.input.model.InputScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InputScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(InputScreenState.initialState())
    val state = _state.asStateFlow()

    fun onWeightChanged(value: String?) {
        _state.update {
            it.copy(weight = value.orEmpty())
        }
    }

    fun onHeightChanged(value: String?) {
        _state.update {
            it.copy(height = value.orEmpty())
        }
    }

    fun onClickToCalculate() {
        // TODO validate data
        // call the next screen
    }
}
