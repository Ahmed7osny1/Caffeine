package com.example.caffeine.presentation.screens.sized_product_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.caffeine.R
import com.example.caffeine.presentation.composable.BackButtonHeader
import com.example.caffeine.presentation.composable.CoffeeSelector
import com.example.caffeine.presentation.composable.CupSize
import com.example.caffeine.presentation.composable.IconTextButton
import com.example.caffeine.presentation.composable.SizeSelector
import com.example.caffeine.presentation.screens.home_screen.coffeeList
import com.example.caffeine.presentation.screens.prepare_progress_screen.navigateToPrepareProgress
import kotlinx.coroutines.launch

@Composable
fun SizedProductScreen(
    navController: NavController,
    viewModel: SizedProductScreenViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    SizedProductContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .systemBarsPadding(),
        productName = state,
        productImage = coffeeList.find { it.name == state }!!.imageView,
        onBackClicked = { navController.popBackStack() },
        onNextClicked = {
            navController.navigateToPrepareProgress(
                name = state
            )
        })
}

@Composable
fun SizedProductContent(
    modifier: Modifier = Modifier,
    productName: String,
    productImage: Int,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    val currentSize = remember { mutableStateOf("M") }
    val currentCoffeeLevel = remember { mutableStateOf("Low") }
    val previousCoffeeLevel = remember { mutableStateOf("") }

    val buttonOffsetY = remember { Animatable(300f) }
    val buttonFade = remember { Animatable(0.2f) }
    val isVisible = remember { mutableStateOf(true) }
    val imageOffset = remember { Animatable(-100f) }
    val coffeeScale = remember { Animatable(0f) }

    remember { mutableStateOf(false) }


    LaunchedEffect(currentCoffeeLevel.value) {
        val targetOffset = 100f
        val levelOrder = mapOf("Low" to 1, "Medium" to 2, "High" to 3)
        val currentLevelValue = levelOrder[currentCoffeeLevel.value] ?: 1
        val previousLevelValue = levelOrder[previousCoffeeLevel.value] ?: 0
        if (currentLevelValue > previousLevelValue || previousLevelValue == 0) {
            imageOffset.snapTo(-1000f)
            imageOffset.animateTo(
                targetValue = targetOffset,
                animationSpec = tween(durationMillis = 1000, easing = EaseInOutCubic)
            )
            launch {
                coffeeScale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
                )
            }
        } else {
            imageOffset.snapTo(100f)
            imageOffset.animateTo(
                targetValue = -1000f,
                animationSpec = tween(durationMillis = 1000, easing = EaseInOutCubic)
            )
            launch {
                coffeeScale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 1000, easing = EaseInOut)
                )
            }
        }
        previousCoffeeLevel.value = currentCoffeeLevel.value
    }

    LaunchedEffect(Unit) {
        buttonOffsetY.animateTo(
            targetValue = 0f, animationSpec = tween(
                durationMillis = 400,
                easing = EaseInOut,

                )
        )
        buttonFade.animateTo(
            targetValue = 1f, animationSpec = tween(
                durationMillis = 400,
            )
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = CenterHorizontally,
    ) {

        BackButtonHeader(
            bottomSpace = 16.dp,
            modifier = Modifier.padding(horizontal = 16.dp),
            title = checkNotNull(productName),
            onBackClick = onBackClicked
        )

        CupSize(
            currentSize = currentSize.value,
            imageOffset = imageOffset.value,
            productImage = productImage
        )

        SizeSelector(
            currentSize = currentSize.value, onClick = {
                currentSize.value = it
            })

        Spacer(modifier = Modifier.height(16.dp))

        CoffeeSelector(
            currentCoffeeLevel = currentCoffeeLevel.value, onClick = {
                currentCoffeeLevel.value = it

            })

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = isVisible.value, exit = fadeOut(tween(durationMillis = 700))
        ) {
            IconTextButton(
                text = "Continue",
                icon = painterResource(R.drawable.arrow_right),
                modifier = Modifier
                    .size(height = 56.dp, width = 162.dp)
                    .offset(y = buttonOffsetY.value.dp)
                    .alpha(buttonFade.value),
                onClick = onNextClicked
            )
        }
        Spacer(Modifier.height(50.dp))
    }
}
