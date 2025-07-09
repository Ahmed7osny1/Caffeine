package com.example.caffeine.presentation.screens.prepare_progress_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PrepareProgressScreenViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    private val prepareProgressArgs = PrepareProgressScreenArgs(savedStateHandle)

    init {
        getName()
    }

    private fun getName() {
        _state.update { prepareProgressArgs.name }
    }
}