package com.example.caffeine.navigation

sealed class AppDestination(val route: String) {
    object StarterScreen: AppDestination(route = "StarterScreen")
    object HomeScreen: AppDestination(route = "HomeScreen")
    object HomeCoffeeReady: AppDestination(route = "HomeCoffeeReady")
    object PrepareProgressScreen: AppDestination(route = "PrepareProgressScreen")
    object SnacksScreen: AppDestination(route = "SnacksScreen")
    object SizedProductScreen: AppDestination(route = "SizedProductScreen")
    object ThankYouScreen: AppDestination(route = "ThankYouScreen")
}