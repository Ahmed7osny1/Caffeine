package com.example.caffeine.presentation.composable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.extension.dropShadow
import com.example.caffeine.presentation.ui.theme.urbanist

@Composable
fun SizeSelector(
    allSizes: List<String> = listOf("S", "M", "L"),
    onClick: (String) -> Unit = {},
    currentSize: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xffF5F5F5))
            .padding(horizontal = 4.dp, vertical = 8.dp)

    ) {
        allSizes.forEach { size ->
            val isSelected = size == currentSize
            val transition = updateTransition(targetState = isSelected)
            val backgroundColor =
                transition.animateColor(transitionSpec = {
                    tween(easing = EaseInOut)
                }) {
                    when (it) {
                        true -> Color(0xFF7C351B)
                        false -> Color.Transparent
                    }
                }

            Box(
                Modifier
                    .padding(horizontal = 4.dp)
                    .then(
                        if (isSelected) Modifier
                            .dropShadow(
                                shape = CircleShape,
                                color = Color(0x80B94B23),
                                blur = 16.dp,
                                offsetY = 6.dp,
                            ) else Modifier
                    )
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { onClick(size) }
                    .background(backgroundColor.value)
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = size,
                    color = if (isSelected) Color(0xDEFFFFFF) else Color(0x991F1F1F),
                    fontSize = 20.sp,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.W700,
                    lineHeight = 20.sp,
                )
            }
        }
    }
}