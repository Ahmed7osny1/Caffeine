package com.example.caffeine.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import com.example.caffeine.screens.home_screen.HomeScreenViewModel
import com.example.caffeine.screens.sized_product_screen.SizedProductScreenViewModel
import com.example.caffeine.screens.thank_you_screen.ThankYouScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeScreenViewModel() }
    viewModel { (savedStateHandle: SavedStateHandle) -> ThankYouScreenViewModel(savedStateHandle) }
    viewModel { (savedStateHandle: SavedStateHandle) -> SizedProductScreenViewModel(savedStateHandle) }
}