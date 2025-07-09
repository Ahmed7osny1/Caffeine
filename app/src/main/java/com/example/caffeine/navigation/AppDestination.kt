package com.example.caffeine.navigation

sealed class AppDestination(val route: String) {
    object StarterScreen : AppDestination(route = "StarterScreen")
    object HomeScreen : AppDestination(route = "HomeScreen")
    object SnacksScreen : AppDestination(route = "SnacksScreen")
}