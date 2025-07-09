package com.example.caffeine.presentation.screens.prepare_progress_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.caffeine.R
import com.example.caffeine.presentation.composable.LinearWavyProgressCanvas
import com.example.caffeine.presentation.screens.home_coffee_ready_screen.navigateToHomeCoffeeReady
import com.example.caffeine.presentation.screens.home_screen.coffeeList
import com.example.caffeine.presentation.ui.theme.urbanist
import kotlinx.coroutines.delay

@Composable
fun PrepareProgressScreen(
    navHostController: NavController, viewModel: PrepareProgressScreenViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    val animatedProgress = rememberAutoLoopedProgress(
        onNextClick = {
            navHostController.navigateToHomeCoffeeReady(
                name = state
            )
        })

    PrepareProgressContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .systemBarsPadding(),
        animatedProgress = animatedProgress,
        productImage = coffeeList.find { it.name == state }!!.imageView
    )
}

@Composable
private fun PrepareProgressContent(
    modifier: Modifier = Modifier,
    animatedProgress: Float,
    productImage: Int,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 129.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(341.dp)
                .fillMaxWidth()

        ) {
            Text(
                "200 ML", style = TextStyle(
                    fontFamily = urbanist,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0x99000000)
                ), modifier = Modifier.padding(top = 64.dp, start = 16.dp)
            )
            Image(
                painter = painterResource(productImage),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 200.dp, height = 244.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(Modifier.height(101.dp))
        LinearWavyProgressCanvas(
            modifier = Modifier.fillMaxWidth(),
            progress = animatedProgress,
            waveHeight = 10.dp,
            color = Color(0xCC000000),
        )
        Spacer(Modifier.height(37.dp))
        BottomScreenContent()
    }
}

@Composable
private fun BottomScreenContent() {
    Text(
        "Almost Done", style = TextStyle(
            fontFamily = urbanist,
            fontWeight = FontWeight.W700,
            fontSize = 22.sp,
            letterSpacing = 0.25.sp,
            color = Color(0xDE1F1F1F)
        )
    )
    Text(
        "Your coffee will be finish in", style = TextStyle(
            fontFamily = urbanist,
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            letterSpacing = 0.25.sp,
            color = Color(0x991F1F1F)
        )
    )
    Spacer(Modifier.height(12.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.size(height = 40.dp, width = 187.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.co),
            contentDescription = null,
            modifier = Modifier.size(42.dp)
        )
        Image(
            painter = painterResource(R.drawable.seperate),
            contentDescription = null,
            modifier = Modifier.size(height = 12.dp, width = 4.dp)
        )
        Image(
            painter = painterResource(R.drawable.ff),
            contentDescription = null,
            modifier = Modifier.size(42.dp)
        )
        Image(
            painter = painterResource(R.drawable.seperate),
            contentDescription = null,
            modifier = Modifier.size(height = 12.dp, width = 4.dp)
        )
        Image(
            painter = painterResource(R.drawable.ee),
            contentDescription = null,
            modifier = Modifier.size(42.dp)
        )
    }
}

@Composable
private fun rememberAutoLoopedProgress(
    onNextClick: () -> Unit
): Float {
    var progress by rememberSaveable { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            while (progress < 1f) {
                delay(200)
                progress += 0.05f
            }
            delay(300)
            while (progress > 0f) {
                delay(200)
                progress -= 0.05f
                if (progress <= 0.5) {
                    onNextClick()
                    break
                }
            }
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "loopedProgress"
    )

    return animatedProgress
}
