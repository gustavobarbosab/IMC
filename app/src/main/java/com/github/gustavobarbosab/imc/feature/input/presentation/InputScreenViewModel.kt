package com.github.gustavobarbosab.imc.feature.input.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.gustavobarbosab.imc.common.UiText
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase.InvalidType
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase.ValidationResult
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCaseImpl
import com.github.gustavobarbosab.imc.feature.input.presentation.model.FieldFeedback
import com.github.gustavobarbosab.imc.feature.input.presentation.model.InputScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InputScreenViewModel(
    private val validatorUseCase: BMIDataValidatorUseCase = BMIDataValidatorUseCaseImpl()
) : ViewModel() {

    private val _state = MutableStateFlow(InputScreenState.initialState())
    val state = _state.asStateFlow()

    // Prós e contras dessa abordagem...
    private val _viewAction = MutableSharedFlow<ViewActions>()
    val viewAction = _viewAction.asSharedFlow()

    fun onWeightChanged(value: String) {
        _state.update {
            it.copy(weight = value)
        }
    }

    fun onHeightChanged(value: String) {
        _state.update {
            it.copy(height = value)
        }
    }

    fun onClickToCalculate() {
        val currentState = _state.value
        when (val result = validatorUseCase.validate(
            weight = currentState.weight,
            height = currentState.height
        )) {
            is ValidationResult.ValidFields -> redirectToCalc(
                result.weightValue,
                result.heightValue
            )

            is ValidationResult.InvalidField -> invalidField(
                result.invalidWeight,
                result.invalidHeight
            )
        }
    }

    private fun invalidField(
        invalidWeight: InvalidType?,
        invalidHeight: InvalidType?
    ) {
        val weightFeedback = when (invalidWeight) {
            InvalidType.Empty -> FieldFeedback.Error(
                UiText.TextString("Campo obrigatório")
            )

            InvalidType.NotMatch -> FieldFeedback.Error(
                UiText.TextString("Peso inválido")
            )

            null -> null
        }

        val heightFeedback = when (invalidHeight) {
            InvalidType.Empty -> FieldFeedback.Error(
                UiText.TextString("Campo obrigatório")
            )

            InvalidType.NotMatch -> FieldFeedback.Error(
                UiText.TextString("Altura inválida")
            )

            null -> null
        }

        _state.update {
            it.copy(
                weightFeedback = weightFeedback,
                heightFeedback = heightFeedback
            )
        }
    }

    private fun redirectToCalc(weight: Float, height: Int) {
        _state.update {
            it.copy(
                weightFeedback = null,
                heightFeedback = null
            )
        }
        viewModelScope.launch {
            _viewAction.emit(ViewActions.RedirectToCalc(weight, height))
        }
    }


    sealed class ViewActions {
        data class RedirectToCalc(
            val weight: Float,
            val height: Int
        ) : ViewActions()
    }
}
