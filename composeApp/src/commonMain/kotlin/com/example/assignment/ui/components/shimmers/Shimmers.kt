package com.example.assignment.ui.components.shimmers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.assignment.ui.utils.shimmerBackground

@Composable
fun TextShimmer(
    widthPercentage: Float,
    height: Dp = 14.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(widthPercentage)
            .height(height)
            .shimmerBackground()
    )
}

@Composable
fun ImageShimmer(
    width: Dp,
    aspectRatio: Float = 1f,
    paddingValues: PaddingValues = PaddingValues(),
    shape: Shape = RoundedCornerShape(5.dp)
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .width(width)
            .aspectRatio(aspectRatio)
            .clip(shape)
            .shimmerBackground()
    )
}