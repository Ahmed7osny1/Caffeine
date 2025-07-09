package com.example.caffeine.presentation.screens.prepare_progress_screen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.presentation.screens.prepare_progress_screen.PrepareProgressScreenArgs.Companion.NAME_ARG

const val PrepareProgressRouteBase = "PrepareProgressScreen"

fun NavController.navigateToPrepareProgress(name: String) {
    navigate("$PrepareProgressRouteBase/$name")
}

fun NavGraphBuilder.prepareProgressRoute(navController: NavController) {
    composable(
        route = "$PrepareProgressRouteBase/{$NAME_ARG}",
        arguments = listOf(navArgument(NAME_ARG) { type = NavType.StringType })
    ) {
        PrepareProgressScreen(navController)
    }
}

class PrepareProgressScreenArgs(savedStateHandle: SavedStateHandle) {
    val name: String = checkNotNull(savedStateHandle[NAME_ARG])

    companion object {
        const val NAME_ARG = "name"
    }
}

