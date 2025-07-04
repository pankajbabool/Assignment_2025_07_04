package com.example.assignment.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.png_app_icon
import com.example.assignment.AppConstants
import com.example.assignment.bridge.platformBridge
import com.example.assignment.ui.navigation.Destination
import com.example.assignment.ui.theme.AppTheme
import com.example.assignment.ui.theme.ColorTransparent
import com.example.assignment.ui.theme.ColorWhitePure
import com.example.assignment.ui.theme.getW500Font
import com.example.assignment.ui.theme.getW700Font
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(true) {
        platformBridge.systemBarColors().setStatusBarColor(color = ColorTransparent.toArgb())
        platformBridge.systemBarColors().setIsStatusBarIconLight(isDarkIcons = false)
        platformBridge.systemBarColors().setNavigationBarColor(color = ColorTransparent.toArgb())
        platformBridge.systemBarColors().setIsNavigationBarIconLight(isDarkIcons = false)
    }

    LaunchedEffect(Unit) {
        delay(AppConstants.SPLASH_DELAY)
        navController.navigate(Destination.Weather) {
            popUpTo(Destination.Splash) {
                inclusive = true
            }
        }
    }

    SplashScreenContent()
}
@Composable
private fun SplashScreenContent() {
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF262626), Color(0xFF0D0D0D)),
                    center = Offset.Unspecified,
                    radius = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.png_app_icon),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = "Weather App",
                style = LocalTextStyle.current.copy(
                    fontFamily = FontFamily(getW700Font()),
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp,
                    color = ColorWhitePure
                ),
            )
        }

        Text(
            text = "Powered by Open Meteo Weather",
            style = LocalTextStyle.current.copy(
                fontFamily = FontFamily(getW500Font()),
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = ColorWhitePure
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    AppTheme {
        SplashScreenContent()
    }
}