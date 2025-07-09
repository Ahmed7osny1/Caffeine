package com.example.caffeine.screens.snacks_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caffeine.R
import com.example.caffeine.composable.CloseButton
import com.example.caffeine.navigation.AppDestination
import com.example.caffeine.screens.thank_you_screen.navigateToThankYou
import com.example.caffeine.ui.theme.urbanist

@Composable
fun SnacksScreen(
    navHostController: NavHostController
) {
    SnacksScreenContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .systemBarsPadding(),
        onClick = {
            navHostController.navigateToThankYou(
                name = R.drawable.cupcake
            )
        }
    )
}

@Composable
fun SnacksScreenContent(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color(0xFFFFFFFF))
            .systemBarsPadding()
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        CloseButton()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Take your snack",
            color = Color(0xDE1F1F1F),
            fontFamily = urbanist,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            letterSpacing = 0.25.sp,
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            VerticalPagerSlider(
                pagerState = rememberPagerState(
                initialPage = 2, initialPageOffsetFraction = 0f, pageCount = { 6 }),
                onItemClick = { onClick(it) }
            )
        }
    }
}
