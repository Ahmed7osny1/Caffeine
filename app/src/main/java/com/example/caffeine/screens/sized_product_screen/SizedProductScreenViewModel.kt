package com.example.caffeine.screens.sized_product_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SizedProductScreenViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    private val sizedProductArgs = SizedProductScreenArgs(savedStateHandle)

    init {
        getName()
    }

    private fun getName() {
        _state.update { sizedProductArgs.name }
    }
}