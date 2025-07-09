package com.example.caffeine.di

import androidx.lifecycle.SavedStateHandle
import com.example.caffeine.presentation.screens.home_coffee_ready_screen.HomeCoffeeReadyScreenViewModel
import com.example.caffeine.presentation.screens.home_screen.HomeScreenViewModel
import com.example.caffeine.presentation.screens.prepare_progress_screen.PrepareProgressScreenViewModel
import com.example.caffeine.presentation.screens.sized_product_screen.SizedProductScreenViewModel
import com.example.caffeine.presentation.screens.thank_you_screen.ThankYouScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeScreenViewModel() }
    viewModel { (savedStateHandle: SavedStateHandle) -> ThankYouScreenViewModel(savedStateHandle) }
    viewModel { (savedStateHandle: SavedStateHandle) -> SizedProductScreenViewModel(savedStateHandle) }
    viewModel { (savedStateHandle: SavedStateHandle) -> PrepareProgressScreenViewModel(savedStateHandle) }
    viewModel { (savedStateHandle: SavedStateHandle) -> HomeCoffeeReadyScreenViewModel(savedStateHandle) }
}