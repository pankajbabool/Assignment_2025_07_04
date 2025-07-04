package com.example.assignment.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun calculateAvailableHeight(): Dp {
    val density = LocalDensity.current
    var height by remember { mutableStateOf(0.dp) }
    Box(Modifier.fillMaxHeight().onGloballyPositioned { height = (it.size.height / density.density).dp })
    return height
}

@Composable
fun calculateAvailableWidth(): Dp {
    val density = LocalDensity.current
    var width by remember { mutableStateOf(0.dp) }
    Box(Modifier.fillMaxWidth().onGloballyPositioned { width = (it.size.width / density.density).dp })
    return width
}