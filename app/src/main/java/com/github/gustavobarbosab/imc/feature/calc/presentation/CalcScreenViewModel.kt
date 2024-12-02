package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.lifecycle.ViewModel
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.BMIWeightState
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalcScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalcScreenState.initialState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                _backgroundColor = BMIWeightState.Overweight.color
            )
        }
    }


    fun showAboutBottomSheet() {
        _state.update {
            it.copy(showAbout = true)
        }
    }

    fun dismissAboutBottomSheet() {
        _state.update {
            it.copy(showAbout = false)
        }
    }
}
