package com.example.caffeine.screens.sized_product_screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.screens.thank_you_screen.ThankYouArgs.Companion.NAME_ARG

const val SizedProductRouteBase = "SizedProductScreen"

fun NavController.navigateToSizedProduct(name: String) {
    navigate("$SizedProductRouteBase/$name")
}

fun NavGraphBuilder.sizedProductRoute(navController: NavController) {
    composable(
        route = "$SizedProductRouteBase/{$NAME_ARG}",
        arguments = listOf(navArgument(NAME_ARG) { type = NavType.StringType })
    ) {
        SizedProductScreen(navController)
    }
}

class SizedProductScreenArgs(savedStateHandle: SavedStateHandle) {
    val name: String = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "name"
    }
}

