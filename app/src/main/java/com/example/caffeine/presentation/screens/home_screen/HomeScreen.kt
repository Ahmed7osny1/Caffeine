package com.example.caffeine.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.caffeine.R
import com.example.caffeine.presentation.composable.CoffeeSlider
import com.example.caffeine.presentation.composable.IconTextButton
import com.example.caffeine.presentation.composable.TopBarContent
import com.example.caffeine.presentation.screens.sized_product_screen.navigateToSizedProduct
import com.example.caffeine.presentation.ui.theme.urbanist

@Composable
fun HomeScreen(
    navHostController: NavHostController, viewModel: HomeScreenViewModel = viewModel()
) {
    HomeScreenContent(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState()), onNextClick = {
        val selectedCoffeeName = viewModel.state.value.name
        navHostController.navigateToSizedProduct(
            name = selectedCoffeeName
        )
    }, onItemSelect = { viewModel.selectCoffee(it) })
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    onItemSelect: (CoffeeUiState) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopBarContent()
        Spacer(Modifier.height(16.dp))
        Text(
            "Good Morning", style = TextStyle(
                fontFamily = urbanist,
                fontWeight = FontWeight.W700,
                fontSize = 36.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFFB3B3B3)
            ), modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            "Ahmed ☀", style = TextStyle(
                fontFamily = urbanist,
                fontWeight = FontWeight.W700,
                fontSize = 36.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xFF3B3B3B)
            ), modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            "What would you like to drink today?", style = TextStyle(
                fontFamily = urbanist,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                letterSpacing = 0.25.sp,
                color = Color(0xCC1F1F1F)
            ), modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(76.dp))
        CoffeeSlider(
            coffeeList = coffeeList, onItemSelected = onItemSelect
        )
        Spacer(Modifier.weight(1f))
        IconTextButton(
            modifier = Modifier
                .size(height = 56.dp, width = 162.dp)
                .align(Alignment.CenterHorizontally),
            text = "Continue",
            icon = painterResource(R.drawable.arrow_right),
            onClick = onNextClick
        )
        Spacer(Modifier.height(50.dp))
    }

}