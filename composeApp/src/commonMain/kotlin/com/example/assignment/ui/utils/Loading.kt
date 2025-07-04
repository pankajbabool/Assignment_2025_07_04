package com.example.assignment.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.assignment.ui.theme.ColorWhitePure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Loading() {
    BasicAlertDialog(onDismissRequest = {  }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = ColorWhitePure,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round
            )
        }
    }
}