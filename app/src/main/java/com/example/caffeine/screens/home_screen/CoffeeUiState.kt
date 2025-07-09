package com.example.caffeine.screens.home_screen

import com.example.caffeine.R

data class CoffeeUiState(
    val name: String = "Espresso",
    val image: Int = R.drawable.espresso,
)

val coffeeList = listOf(
    CoffeeUiState(
        name = "Espresso",
        image = R.drawable.espresso
    ),
    CoffeeUiState(
        name = "Macchiato",
        image = R.drawable.macchiato
    ),
    CoffeeUiState(
        name = "Latte",
        image = R.drawable.latte
    ),
    CoffeeUiState(
        name = "Black",
        image = R.drawable.black
    ),
)
