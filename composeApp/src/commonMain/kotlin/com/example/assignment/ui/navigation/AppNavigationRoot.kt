package com.example.assignment.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignment.ui.screens.splash.SplashScreen
import com.example.assignment.ui.screens.weather.WeatherScreen
import com.example.assignment.ui.utils.clearFocusAndHideKeyboardOnOutsideClick

@Composable
fun AppNavigationRoot(
    navController: NavHostController = rememberNavController(),
    durationMillis: Int = 700
) {
    NavHost(
        modifier = Modifier.clearFocusAndHideKeyboardOnOutsideClick(),
        navController = navController,
        startDestination = Destination.Splash,
        enterTransition = { slideIntoContainer(SlideDirection.Left, tween(durationMillis)) },
        exitTransition = { slideOutOfContainer(SlideDirection.Left, tween(durationMillis)) },
        popEnterTransition = { slideIntoContainer(SlideDirection.Right, tween(durationMillis)) },
        popExitTransition = { slideOutOfContainer(SlideDirection.Right, tween(durationMillis)) }
    ) {
        composable<Destination.Splash> { SplashScreen(navController) }
        composable<Destination.Weather> { WeatherScreen() }
    }
}