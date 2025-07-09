package com.example.caffeine.screens.thank_you_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.caffeine.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ThankYouScreenViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow(R.drawable.espresso)
    val state = _state.asStateFlow()

    private val thankYouArgs = ThankYouArgs(savedStateHandle)

    init {
        getName()
    }

    private fun getName() {
        _state.update { thankYouArgs.name }
    }
}