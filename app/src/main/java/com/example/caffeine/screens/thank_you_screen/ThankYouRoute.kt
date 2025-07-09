package com.example.caffeine.screens.thank_you_screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.screens.thank_you_screen.ThankYouArgs.Companion.NAME_ARG

const val ThankYouRouteBase = "thankYouScreen"

fun NavController.navigateToThankYou(name: Int) {
    navigate("$ThankYouRouteBase/$name")
}

fun NavGraphBuilder.thankYouRoute(navController: NavController) {
    composable(
        route = "$ThankYouRouteBase/{$NAME_ARG}",
        arguments = listOf(navArgument(NAME_ARG) { type = NavType.IntType })
    ) {
        ThankYouScreen(navController)
    }
}

class ThankYouArgs(savedStateHandle: SavedStateHandle) {
    val name: Int = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "name"
    }
}

