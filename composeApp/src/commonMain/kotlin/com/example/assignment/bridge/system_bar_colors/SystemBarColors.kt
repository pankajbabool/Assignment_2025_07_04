package com.example.assignment.bridge.system_bar_colors

interface SystemBarColors {
    fun setStatusBarColor(color: Int)
    fun setIsStatusBarIconLight(isDarkIcons: Boolean)
    fun setNavigationBarColor(color: Int)
    fun setIsNavigationBarIconLight(isDarkIcons: Boolean)
}