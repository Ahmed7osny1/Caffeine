package com.example.caffeine.presentation.composable

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.extension.dropShadow

@Composable
fun CoffeeSwitcherButton(
    productLoader: Int,
) {

    val isChecked = remember { mutableStateOf(true) }
    val paddingStart by animateDpAsState(
        if (isChecked.value) 40.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val paddingEnd by animateDpAsState(
        if (isChecked.value) 0.dp else 40.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(width = 78.dp, height = 40.dp)
                .clip(RoundedCornerShape(100.dp))
                .clickable { isChecked.value = !isChecked.value }
                .background(if (isChecked.value) Color(0xFFFFEEE7) else Color(0xFF7C351B)),
        ) {

            Text(
                text = if (isChecked.value) "OFF" else "ON",
                color = if (isChecked.value) Color(0x99000000) else Color(0x99FFFFFF),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                modifier = if (isChecked.value) Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 14.dp)
                else Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 14.dp),

                )

            Image(
                painter = painterResource(productLoader),
                contentDescription = "coffee switch",
                modifier = Modifier.padding(start = paddingStart, end = paddingEnd)
            )
        }
        Text(
            text = "Take Away",
            color = Color(0xB21F1F1F),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}