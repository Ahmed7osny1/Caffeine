package com.example.caffeine.screens.starter_screen

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.caffeine.R
import com.example.caffeine.composable.IconTextButton
import com.example.caffeine.composable.TopBarContent
import com.example.caffeine.navigation.AppDestination
import com.example.caffeine.ui.theme.sniglet

@Composable
fun StarterScreen(
    navController: NavController
) {
    StarterScreenContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .systemBarsPadding(),
        onNextClick = { navController.navigate(AppDestination.HomeScreen.route) })
}

@Composable
private fun StarterScreenContent(
    modifier: Modifier = Modifier, onNextClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarContent()
        Spacer(Modifier.height(24.dp))
        FadingStarIcon()
        Spacer(Modifier.height(33.dp))
        FloatingGhostWithShadow()
        Spacer(Modifier.weight(1f))
        IconTextButton(
            modifier = Modifier.size(height = 56.dp, width = 215.dp),
            onClick = onNextClick,
            text = "bring my coffee",
            icon = painterResource(R.drawable.coffee_icon)
        )
        Spacer(Modifier.height(50.dp))
    }
}

@Composable
private fun FadingStarIcon() {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            alpha.animateTo(
                1f, animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
            )
            alpha.animateTo(
                0.1f, animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
            )
        }
    }

    Box {
        Text(
            text = "Hocus\n" + "Pocus\n" + "I Need Coffee\n" + "to Focus", style = TextStyle(
                fontFamily = sniglet,
                fontWeight = FontWeight.W400,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xDE1F1F1F)
            ), textAlign = TextAlign.Center, modifier = Modifier.padding(top = 20.dp, start = 16.dp)
        )
        Image(
            painter = painterResource(R.drawable.star_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 65.dp, end = 10.dp)
                .size(16.dp)
                .graphicsLayer(alpha = alpha.value)
                .align(Alignment.TopStart)

        )
        Image(
            painter = painterResource(R.drawable.star_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 6.dp, end = 1.dp)
                .size(16.dp)
                .graphicsLayer(alpha = alpha.value)
                .align(Alignment.TopEnd)

        )
        Image(
            painter = painterResource(R.drawable.star_icon),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.BottomEnd)
                .offset(x = (18).dp)
                .graphicsLayer(alpha = alpha.value)
        )
    }
}

@Composable
fun FloatingGhostWithShadow() {
    val offsetY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            offsetY.animateTo(
                -10f, animationSpec = tween(durationMillis = 1200, easing = LinearEasing)
            )
            offsetY.animateTo(
                10f, animationSpec = tween(durationMillis = 1200, easing = LinearEasing)
            )
        }
    }

    val normalized = ((offsetY.value + 10f) / 40f)
    val shadowAlpha = (0.05f + (normalized * 0.2f)).coerceIn(0.05f, 0.25f)
    val shadowColor = Color(0xFF1F1F1F).copy(alpha = shadowAlpha)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.coffee_ghost),
            contentDescription = null,
            modifier = Modifier
                .size(244.dp)
                .offset(y = offsetY.value.dp)
        )
        Box(
            modifier = Modifier
                .size(178.dp, 28.dp)
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val paint = Paint().apply {
                            color = shadowColor
                            asFrameworkPaint().maskFilter =
                                BlurMaskFilter(15f, BlurMaskFilter.Blur.NORMAL)
                        }
                        canvas.drawOval(Rect(Offset(0f, 4f), size), paint)
                    }
                })
    }
}
