package com.github.gustavobarbosab.imc.feature.about

import androidx.lifecycle.ViewModel
import com.github.gustavobarbosab.imc.feature.about.model.AboutBMIScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutBMIViewModel : ViewModel() {

    private val _state = MutableStateFlow(AboutBMIScreenState())
    val state = _state.asStateFlow()

}