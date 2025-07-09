package com.example.caffeine.presentation.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.presentation.ui.theme.urbanist

@Composable
fun BackButtonHeader(
    modifier: Modifier = Modifier,
    title: String,
    bottomSpace: Dp,
    onBackClick: () -> Unit,
) {
    val headerOffsetY = remember { Animatable(300f) }
    val headerFade = remember { Animatable(0.2f) }

    LaunchedEffect(Unit) {
        headerOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 600,
                easing = EaseInOut,

                )
        )
        headerFade.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 200,
            )
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = bottomSpace)
            .offset(y = headerOffsetY.value.dp)
            .alpha(headerFade.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape)
                .clickable(onClick = onBackClick)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = "back",
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = title,
            color = Color(0xDE1F1F1F),
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            fontFamily = urbanist
        )
    }
}
