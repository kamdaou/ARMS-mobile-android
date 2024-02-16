package com.amalitech.arms_mobile.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun Shimmer(modifier: Modifier = Modifier, shape: Dp = 16.dp) {
    Box(modifier = modifier.shimmerEffect(shape))
}


fun Modifier.shimmerEffect(shape: Dp = 16.dp): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }

    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        label = "",
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(Color(0xffdcdcdc), Color(0xffefefef), Color(0xffdcdcdc)),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
        shape = RoundedCornerShape(shape)
    ).onGloballyPositioned { size = it.size }
}


@Composable
fun HorizontalShimmer(modifier: Modifier = Modifier) {
    Row {
        repeat(2) {
            Column(
                modifier = modifier
            ) {
                Shimmer(
                    shape = 16.dp,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Shimmer(
                    shape = 16.dp,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()

                )
            }
        }
    }
}