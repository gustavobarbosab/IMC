package com.github.gustavobarbosab.imc.feature.calc.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.feature.calc.data.usecase.BMICalculatorUseCase
import com.github.gustavobarbosab.imc.feature.calc.data.usecase.BMICalculatorUseCaseImpl
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenBMIBackgroundColor
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.CalcScreenState
import com.github.gustavobarbosab.imc.feature.calc.presentation.model.mapToBackgroundColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalcScreenViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val calculatorUseCase: BMICalculatorUseCase = BMICalculatorUseCaseImpl()
    private val _state = MutableStateFlow(CalcScreenState.initialState())
    val state = _state.asStateFlow()

    init {
        val route = savedStateHandle.toRoute<CalcScreenRoute>()
        val calculatedDataResult = calculatorUseCase.calculate(route.weight, route.height)

        val (bmi, feedback, backgroundColorType) = when (calculatedDataResult) {
            is BMICalculatorUseCase.Result.CalculatedData -> Triple(
                UiText.TextString(calculatedDataResult.bmi.bmiValue),
                UiText.TextResource(calculatedDataResult.bmi.feedback),
                calculatedDataResult.bmi.mapToBackgroundColor()
            )

            BMICalculatorUseCase.Result.InvalidData -> Triple(
                UiText.TextString("--"),
                UiText.TextString("Dados inválidos..."),
                CalcScreenBMIBackgroundColor.Default
            )
        }

        _state.update {
            it.copy(
                bmi = bmi,
                message = feedback,
                backgroundColorList = backgroundColorType.color
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
