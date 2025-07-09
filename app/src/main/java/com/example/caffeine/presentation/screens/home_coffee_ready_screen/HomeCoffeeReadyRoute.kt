package com.example.caffeine.presentation.screens.home_coffee_ready_screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.presentation.screens.home_coffee_ready_screen.HomeCoffeeReadyRouteBaseArgs.Companion.NAME_ARG

const val homeCoffeeReadyRouteBase = "HomeCoffeeReadyScreen"

fun NavController.navigateToHomeCoffeeReady(name: String) {
    navigate("$homeCoffeeReadyRouteBase/$name")
}

fun NavGraphBuilder.homeCoffeeReadyRoute(navController: NavController) {
    composable(
        route = "$homeCoffeeReadyRouteBase/{$NAME_ARG}",
        arguments = listOf(navArgument(NAME_ARG) { type = NavType.StringType })
    ) {
        HomeCoffeeReadyScreen(navController)
    }
}

class HomeCoffeeReadyRouteBaseArgs(savedStateHandle: SavedStateHandle) {
    val name: String = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "name"
    }
}

