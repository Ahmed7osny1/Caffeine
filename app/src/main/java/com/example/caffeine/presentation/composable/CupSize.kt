package com.example.caffeine.presentation.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.presentation.ui.theme.urbanist

@Composable
fun CupSize(
    modifier: Modifier = Modifier,
    currentSize: String,
    imageOffset: Float,
    productImage: Int
) {
    val size = when (currentSize) {
        "S" -> 200
        "M" -> 250
        else -> 300
    }
    val textSize = when (currentSize) {
        "S" -> "150"
        "M" -> "200"
        else -> "400"
    }

    val animatedSize by animateDpAsState(
        targetValue = size.dp,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOutCubic
        )
    )

    Box(
        modifier = modifier.size(width = 360.dp, height = 341.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.coffee),
            contentDescription = "add coffee",
            modifier = modifier
                .size(size = 100.dp)
                .align(Alignment.TopCenter)
                .offset(y = imageOffset.dp)
        )
        Image(
            painter = painterResource(productImage),
            contentDescription = null,
            modifier = Modifier.size(size = animatedSize),
        )

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(size = 64.dp),
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            AnimatedContent(
                targetState = textSize,
                transitionSpec = {
                    fadeIn(
                        tween(
                            durationMillis = 500,
                            easing = EaseOutBack
                        )
                    ) togetherWith fadeOut()
                }
            ) { targetSize ->
                Text(
                    text = "$targetSize ML",
                    modifier = Modifier
                        .padding(top = 48.dp),
                    color = Color(0x99000000),
                    fontSize = 14.sp,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}
