package com.github.gustavobarbosab.imc.feature.input.data.usecase

import androidx.core.text.isDigitsOnly
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase.InvalidType
import com.github.gustavobarbosab.imc.feature.input.data.usecase.BMIDataValidatorUseCase.ValidationResult

interface BMIDataValidatorUseCase {

    fun validate(
        weight: String?,
        height: String?
    ): ValidationResult

    sealed class ValidationResult {
        data class InvalidField(
            val invalidWeight: InvalidType?,
            val invalidHeight: InvalidType?
        ) : ValidationResult()

        data class ValidFields(
            val weightValue: Float,
            val heightValue: Int
        ) : ValidationResult()
    }

    sealed class InvalidType {
        data object Empty : InvalidType()
        data object NotMatch : InvalidType()
    }
}

class BMIDataValidatorUseCaseImpl : BMIDataValidatorUseCase {

    private val validationRegex = Regex("\\d{1,3}[.,]?\\d{0,2}")

    override fun validate(
        weight: String?,
        height: String?
    ): ValidationResult {
        val weightInvalidType: InvalidType? = when {
            weight.isNullOrBlank() -> InvalidType.Empty
            !validationRegex.matches(weight) -> InvalidType.NotMatch
            else -> null
        }

        val heightInvalidType: InvalidType? = when {
            height.isNullOrBlank() -> InvalidType.Empty
            !height.isDigitsOnly() -> InvalidType.NotMatch
            else -> null
        }

        if (weightInvalidType == null && heightInvalidType == null) {
            return ValidationResult.ValidFields(
                weight.convertToFloat(),
                height.convertToInt()
            )
        }

        return ValidationResult.InvalidField(
            weightInvalidType,
            heightInvalidType
        )
    }

    private fun String?.convertToFloat(): Float = this?.replace(',', '.')?.toFloat() ?: 0f

    private fun String?.convertToInt(): Int = this?.toInt() ?: 0

}
