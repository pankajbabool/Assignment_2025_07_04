package com.example.assignment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        activityProvider = this
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setTransparentNavBar()
        setContent {
            AppEntryPoint()
        }
    }

    private fun setTransparentNavBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityProvider = null
    }

    companion object {
        var activityProvider: AppActivity? = null
    }
}