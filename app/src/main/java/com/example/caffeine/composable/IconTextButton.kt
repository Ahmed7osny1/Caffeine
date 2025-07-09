package com.example.caffeine.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeine.extension.dropShadow
import com.example.caffeine.ui.theme.urbanist

@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String, icon: Painter
) {
    Box(
        modifier = modifier
            .dropShadow(
                blur = 12.dp,
                shape = ButtonDefaults.shape,
                color = Color(0x3D000000),
                offsetY = 6.dp
            )
            .clip(shape = RoundedCornerShape(100))
            .background(color = Color(0xFF1f1f1f))
            .clickable(onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text,
                color = Color(0xDEFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = urbanist,
                modifier = Modifier.padding(end = 8.dp),
            )
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color(0xDEFFFFFF),
            )
        }
    }
}