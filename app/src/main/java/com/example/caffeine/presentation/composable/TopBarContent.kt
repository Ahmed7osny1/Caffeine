package com.example.caffeine.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.caffeine.R

@Composable
fun TopBarContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp)
        )
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .clip(shape = CircleShape)
                .size(48.dp)
                .background(color = Color(0xFFF5F5F5)), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.add_icon),
                contentDescription = null,
                modifier = Modifier.size(19.dp)
            )
        }
    }
}