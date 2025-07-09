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
import com.example.caffeine.screens.PrepareProgressScreen
import com.example.caffeine.screens.home_coffee_ready.HomeCoffeeReady
import com.example.caffeine.screens.home_screen.HomeScreen
import com.example.caffeine.screens.snacks_screen.SnacksScreen
import com.example.caffeine.screens.starter_screen.StarterScreen
import com.example.caffeine.screens.thank_you_screen.thankYouRoute

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
        }
    ) {

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
            route = AppDestination.HomeCoffeeReady.route,
        ) {
            HomeCoffeeReady(navHostController)
        }
        composable(
            route = AppDestination.PrepareProgressScreen.route,
        ) {
            PrepareProgressScreen(navHostController)
        }
        composable(
            route = AppDestination.SnacksScreen.route,
        ) {
            SnacksScreen(navHostController)
        }

        thankYouRoute(navController = navHostController)

    }
}