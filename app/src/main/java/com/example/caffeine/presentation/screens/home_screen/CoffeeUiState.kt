package com.example.caffeine.presentation.screens.home_screen

import com.example.caffeine.R

data class CoffeeUiState(
    val name: String = "Espresso",
    val image: Int = R.drawable.espresso,
    val imageView: Int = R.drawable.espresso,
    val imageViewCover: Int = R.drawable.espresso_cover,
    val imageLoading: Int = R.drawable.black_top,
)

val coffeeList = listOf(
    CoffeeUiState(
        name = "Espresso",
        image = R.drawable.espresso,
        imageView = R.drawable.black_empty_cup,
        imageViewCover = R.drawable.macchiato_cover,
        imageLoading = R.drawable.black_top,
    ),
    CoffeeUiState(
        name = "Macchiato",
        image = R.drawable.macchiato,
        imageView = R.drawable.macchiato_view,
        imageViewCover = R.drawable.espresso_cover,
        imageLoading = R.drawable.macchiato_top,
    ),
    CoffeeUiState(
        name = "Latte",
        image = R.drawable.latte,
        imageView = R.drawable.espresso_empty_cup,
        imageViewCover = R.drawable.macchiato_cover,
        imageLoading = R.drawable.lattee_cover,
    ),
    CoffeeUiState(
        name = "Black",
        image = R.drawable.black,
        imageView = R.drawable.espresso_empty_cup,
        imageViewCover = R.drawable.espresso_cover,
        imageLoading = R.drawable.latte_empty_cup,
    ),
)
