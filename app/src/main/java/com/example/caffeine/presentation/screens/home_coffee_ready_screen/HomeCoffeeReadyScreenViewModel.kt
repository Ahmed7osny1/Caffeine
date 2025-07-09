package com.example.caffeine.presentation.screens.home_coffee_ready_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeCoffeeReadyScreenViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    private val homeCoffeeReadyArgs = HomeCoffeeReadyRouteBaseArgs(savedStateHandle)

    init {
        getName()
    }

    private fun getName() {
        _state.update { homeCoffeeReadyArgs.name }
    }
}