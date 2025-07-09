package com.example.caffeine.composable

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun LinearWavyProgressCanvas(
    modifier: Modifier = Modifier,
    progress: Float,
    wavelength: Dp = 80.dp,
    waveHeight: Dp = 12.dp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val waveLengthPx = with(LocalDensity.current) { wavelength.toPx() }
    val waveHeightPx = with(LocalDensity.current) { waveHeight.toPx() }

    Canvas(modifier = modifier) {
        val width = size.width * progress
        val height = size.height
        val path = Path()

        var x = 0f
        path.moveTo(x, height / 2)

        while (x <= width) {
            val y = (sin((x / waveLengthPx) * 2 * PI) * waveHeightPx).toFloat() + height / 2
            path.lineTo(x, y)
            x += 1f
        }

        drawPath(path, color = color, style = Stroke(width = 2.dp.toPx()))
    }
}
