package com.example.caffeine.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.caffeine.presentation.screens.home_coffee_ready_screen.homeCoffeeReadyRoute
import com.example.caffeine.presentation.screens.home_screen.HomeScreen
import com.example.caffeine.presentation.screens.prepare_progress_screen.prepareProgressRoute
import com.example.caffeine.presentation.screens.sized_product_screen.sizedProductRoute
import com.example.caffeine.presentation.screens.snacks_screen.SnacksScreen
import com.example.caffeine.presentation.screens.starter_screen.StarterScreen
import com.example.caffeine.presentation.screens.thank_you_screen.thankYouRoute

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AppDestination.StarterScreen.route,
        enterTransition = {
            fadeIn(animationSpec = tween(500)) + scaleIn(initialScale = 1.2f)
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500)) + scaleOut(targetScale = 1.2f)
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(500))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(500))
        }) {

        composable(
            route = AppDestination.StarterScreen.route,
        ) {
            StarterScreen(navHostController)
        }

        composable(
            route = AppDestination.HomeScreen.route,
        ) {
            HomeScreen(navHostController)
        }
        composable(
            route = AppDestination.SnacksScreen.route,
        ) {
            SnacksScreen(navHostController)
        }

        homeCoffeeReadyRoute(navController = navHostController)

        sizedProductRoute(navController = navHostController)

        prepareProgressRoute(navController = navHostController)

        thankYouRoute(navController = navHostController)

    }
}