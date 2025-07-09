package com.example.caffeine.presentation.screens.thank_you_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.caffeine.R
import com.example.caffeine.presentation.composable.CloseButton
import com.example.caffeine.presentation.composable.IconTextButton
import com.example.caffeine.navigation.AppDestination
import com.example.caffeine.presentation.ui.theme.sniglet

@Composable
fun ThankYouScreen(
    navController: NavController,
    viewModel: ThankYouScreenViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    ThankYouContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .systemBarsPadding(),
        state = state,
        onBackClick = {
            navController.popBackStack(
                route = AppDestination.StarterScreen.route, inclusive = false
            )
        })
}

@Composable
fun ThankYouContent(
    modifier: Modifier = Modifier, state: Int, onBackClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CloseButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, start = 16.dp)
        )
        Spacer(Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.coffee_beans),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "More Espresso, Less Depresso", style = TextStyle(
                    fontFamily = sniglet,
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xFF7C351B)
                )
            )
            Image(
                painter = painterResource(R.drawable.coffee_beans),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(Modifier.height(24.dp))
        Image(
            painter = painterResource(state),
            contentDescription = null,
            modifier = Modifier.size(width = 300.dp, height = 310.dp)
        )
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Bon appétit", style = TextStyle(
                    fontFamily = sniglet,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                    letterSpacing = 0.25.sp,
                    color = Color(0xCC1F1F1F)
                )
            )
            Image(
                painter = painterResource(R.drawable.magic_wand),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(bottom = 2.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        IconTextButton(
            text = "Thank youuu",
            icon = painterResource(R.drawable.arrow_right),
            onClick = onBackClick,
            modifier = Modifier.size(height = 56.dp, width = 192.dp)
        )
        Spacer(Modifier.height(50.dp))
    }
}