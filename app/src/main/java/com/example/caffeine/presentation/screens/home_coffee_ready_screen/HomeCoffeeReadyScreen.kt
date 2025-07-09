package com.example.caffeine.presentation.screens.home_coffee_ready_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.caffeine.R
import com.example.caffeine.presentation.composable.CloseButton
import com.example.caffeine.presentation.composable.CoffeeSwitcherButton
import com.example.caffeine.presentation.composable.IconTextButton
import com.example.caffeine.presentation.composable.ReadySection
import com.example.caffeine.navigation.AppDestination
import com.example.caffeine.presentation.screens.home_screen.coffeeList
import com.example.caffeine.presentation.screens.sized_product_screen.SizedProductScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeCoffeeReadyScreen(
    navHostController: NavController, viewModel: SizedProductScreenViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    HomeCoffeeReadyContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .systemBarsPadding(),
        productImage = coffeeList.find { it.name == state }!!.imageView,
        productImageCover = coffeeList.find { it.name == state }!!.imageViewCover,
        productLoader = coffeeList.find { it.name == state }!!.imageLoading,
        onTakeSnackClick = {
            navHostController.navigate(
                route = AppDestination.SnacksScreen.route
            )
        },
        onCloseClick = {
            navHostController.popBackStack(
                route = AppDestination.StarterScreen.route, inclusive = false
            )
        })
}

@Composable
fun HomeCoffeeReadyContent(
    modifier: Modifier = Modifier,
    productImage: Int,
    productImageCover: Int,
    productLoader: Int,
    onTakeSnackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {

    val cupOffset = remember { Animatable(-200f) }
    val readySectionOffset = remember { Animatable(-200f) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            cupOffset.animateTo(
                -67f, animationSpec = TweenSpec(durationMillis = 2000)
            )
        }
        readySectionOffset.animateTo(
            0f, animationSpec = TweenSpec(durationMillis = 2000)
        )
    }

    val snackButtonOffset = remember { Animatable(200f) }
    LaunchedEffect(Unit) {
        snackButtonOffset.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CloseButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
                .offset(y = readySectionOffset.value.dp)
                .clickable(onClick = onCloseClick),
        )

        Spacer(Modifier.height(16.dp))

        ReadySection(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .offset(y = readySectionOffset.value.dp)
        )

        Box(
            modifier = Modifier
                .width(260.dp)
                .height(330.dp),
        ) {
            Image(
                painter = painterResource(productImageCover),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 50.dp)
                    .zIndex(1f)
                    .size(height = 69.dp, width = 260.dp)
                    .offset(y = cupOffset.value.dp)
            )
            Image(
                painter = painterResource(productImage),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 245.dp, height = 300.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            Image(
                modifier = Modifier
                    .size(66.dp)
                    .align(alignment = Alignment.Center),
                contentDescription = null,
                painter = painterResource(R.drawable.logo)
            )
        }

        Spacer(Modifier.weight(1f))

        CoffeeSwitcherButton(
            productLoader = productLoader
        )

        Spacer(Modifier.height(16.dp))

        IconTextButton(
            text = "Take snack",
            icon = painterResource(R.drawable.arrow_right),
            onClick = onTakeSnackClick,
            modifier = Modifier
                .size(width = 180.dp, height = 56.dp)
                .offset(y = snackButtonOffset.value.dp)
        )
        Spacer(Modifier.height(50.dp))
    }

}

