package com.example.caffeine.screens.home_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(CoffeeUiState())
    val state = _state.asStateFlow()

    fun selectCoffee(coffee: CoffeeUiState) {
        _state.value = coffee
    }
}
