package com.example.assignment

import androidx.compose.ui.window.ComposeUIViewController
import com.example.assignment.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    AppEntryPoint()
}