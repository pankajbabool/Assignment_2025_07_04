package com.example.assignment

import androidx.compose.runtime.Composable
import com.example.assignment.ui.navigation.AppNavigationRoot
import com.example.assignment.ui.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
fun AppEntryPoint() {
    AppTheme {
        KoinContext {
            AppNavigationRoot()
        }
    }
}