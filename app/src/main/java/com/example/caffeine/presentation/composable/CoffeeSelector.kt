package com.example.caffeine.presentation.composable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.R
import com.example.caffeine.extension.dropShadow
import com.example.caffeine.presentation.ui.theme.urbanist

@Composable
fun CoffeeSelector(
    allLevels: List<String> = listOf("Low", "Medium", "High"),
    onClick: (String) -> Unit = {},
    currentCoffeeLevel: String
) {
    Column {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xffF5F5F5))
                .padding(horizontal = 4.dp, vertical = 8.dp)
        ) {

            allLevels.forEach { level ->
                val isSelected = level == currentCoffeeLevel
                val transition = updateTransition(targetState = isSelected)
                val backgroundColor = transition.animateColor(
                    transitionSpec = { tween(easing = EaseInOut) }) {
                    when (it) {
                        true -> Color(0xFF7C351B)
                        false -> Color.Transparent
                    }
                }

                Box(
                    Modifier
                        .padding(horizontal = 4.dp)
                        .then(
                            if (isSelected) Modifier.dropShadow(
                                shape = CircleShape,
                                color = Color(0x80B94B23),
                                blur = 16.dp,
                                offsetY = 6.dp,
                            ) else Modifier
                        )
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onClick(level) }
                        .background(backgroundColor.value)
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.coffee_beans),
                        contentDescription = "Product Image",
                        tint = if (isSelected) Color(0xDEFFFFFF) else Color.Transparent,
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.Top, modifier = Modifier.padding(horizontal = 4.dp)

        ) {

            allLevels.forEach { level ->
                Text(
                    text = level,
                    color = Color(0x991F1F1F),
                    fontSize = 10.sp,
                    fontFamily = urbanist,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}