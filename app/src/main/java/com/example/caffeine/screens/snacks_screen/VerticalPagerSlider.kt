package com.example.caffeine.screens.snacks_screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.caffeine.R
import com.example.caffeine.extension.dropShadow
import kotlin.math.abs

@Composable
fun VerticalPagerSlider(
    pagerState: PagerState,
    onItemClick: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    VerticalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .offset(x = -screenWidth * 0.25f),
        contentPadding = PaddingValues(vertical = screenHeight * 0.23f),
        flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState, snapAnimationSpec = tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        )
    ) { page ->

        val pageOffset =
            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction


        val clampedOffset = pageOffset.coerceIn(-1f, 1f)

        val scale = 1f - 0.1f * abs(clampedOffset)

        val rotation = clampedOffset * -8f

        val offsetX = when {
            pageOffset < -1f -> lerp(
                screenWidth.value * 0.4f * -1f,
                screenWidth.value * 0.8f * -2f,
                (pageOffset + 1f) / -1f
            )

            pageOffset < 0f -> screenWidth.value * 0.4f * pageOffset
            else -> -screenWidth.value * 0.5f * pageOffset
        }

        val offsetY = when {
            pageOffset < -1f -> lerp(
                screenHeight.value * 0.06f * -1f,
                screenHeight.value * 0.1f * -2f,
                (pageOffset + 1f) / -1f
            )

            pageOffset < 0f -> screenHeight.value * 0.10f * pageOffset
            else -> screenHeight.value * 0.5f * pageOffset
        }

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale * 1.1f
                    scaleY = scale * 1.12f
                    rotationZ = rotation
                    translationX = offsetX
                    translationY = offsetY
                }
                .defaultMinSize(
                    minWidth = screenWidth * 0.75f,
                    minHeight = screenHeight * 0.35f
                ).dropShadow(
                    shape = RoundedCornerShape(32.dp),
                    color = Color(0x1F000000),
                    blur = 20.dp,
                    offsetY = 4.dp
                )
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(32.dp)
                )
                .clickable(
                    onClick = { onItemClick(sweat[page]) },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(sweat[page]),
                contentDescription = null,
                modifier = Modifier.size(screenWidth * 0.4f)
            )
        }
    }
}

val sweat = listOf(
    R.drawable.chocolate,
    R.drawable.cupcake,
    R.drawable.cookies,
    R.drawable.cinnabon,
    R.drawable.croissan,
    R.drawable.oreo,
)
