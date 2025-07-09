package com.example.caffeine.presentation.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.example.caffeine.R
import com.example.caffeine.presentation.screens.home_screen.CoffeeUiState
import com.example.caffeine.presentation.ui.theme.urbanist
import kotlin.math.absoluteValue

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CoffeeSlider(
    modifier: Modifier = Modifier,
    coffeeList: List<CoffeeUiState>,
    onItemSelected: (CoffeeUiState) -> Unit
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp

    val itemWidth = 200.dp
    val paddingValue = (screenWidth - itemWidth) / 2

    val pagerState = rememberPagerState(
        initialPage = coffeeList.size - 1, pageCount = { coffeeList.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        onItemSelected(coffeeList[pagerState.currentPage])
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 5.dp,
        verticalAlignment = Alignment.CenterVertically,
        pageSize = PageSize.Fixed(itemWidth),
        contentPadding = PaddingValues(horizontal = paddingValue),
        modifier = modifier.fillMaxWidth()
    ) { page ->

        val coffee = coffeeList[page]

        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        Box(
            modifier = Modifier
                .size(width = itemWidth, height = 240.dp)
                .graphicsLayer {
                    scaleX = lerp(0.5f, 1.25f, 1f - pageOffset.coerceIn(0f, 1f))
                    scaleY = scaleX
                    alpha = lerp(1f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                }
                .zIndex(1f - pageOffset)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(coffee.image),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = coffee.name, style = TextStyle(
                        fontFamily = urbanist,
                        fontWeight = FontWeight.W700,
                        fontSize = 32.sp,
                        letterSpacing = 0.25.sp,
                        color = Color(0xFF3B3B3B)
                    ), modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

