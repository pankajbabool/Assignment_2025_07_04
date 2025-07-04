package com.example.assignment.bridge.system_bar_colors

import androidx.core.view.WindowCompat
import com.example.assignment.AppActivity

class AndroidSystemBarColors: SystemBarColors {

    private val activity get() = AppActivity.activityProvider

    @Suppress("DEPRECATION")
    override fun setStatusBarColor(color: Int) {
        activity?.window?.statusBarColor = color
    }

    override fun setIsStatusBarIconLight(isDarkIcons: Boolean) {
        activity?.window?.let { window -> WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = isDarkIcons }
    }

    @Suppress("DEPRECATION")
    override fun setNavigationBarColor(color: Int) {
        activity?.window?.navigationBarColor = color
    }

    override fun setIsNavigationBarIconLight(isDarkIcons: Boolean) {
        activity?.window?.let { window -> WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightNavigationBars = isDarkIcons }
    }
}