package com.example.assignment.ui.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.png_storm_with_rain
import assignment.composeapp.generated.resources.svg_location_marker_outline
import assignment.composeapp.generated.resources.svg_wind
import com.example.assignment.bridge.platformBridge
import com.example.assignment.ui.components.alert_dialogs.AlertDialogType1
import com.example.assignment.ui.theme.AppTheme
import com.example.assignment.ui.theme.ColorTransparent
import com.example.assignment.ui.theme.ColorWhitePure
import com.example.assignment.ui.theme.getW500Font
import com.example.assignment.ui.utils.Loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WeatherScreen() {
    LaunchedEffect(true) {
        platformBridge.systemBarColors().setStatusBarColor(color = ColorTransparent.toArgb())
        platformBridge.systemBarColors().setIsStatusBarIconLight(isDarkIcons = false)
        platformBridge.systemBarColors().setNavigationBarColor(color = ColorTransparent.toArgb())
        platformBridge.systemBarColors().setIsNavigationBarIconLight(isDarkIcons = false)
    }

    val viewModel = viewModel { WeatherViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WeatherScreenContent(
        state = uiState,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherScreenContent(
    state: WeatherScreenUiState = WeatherScreenUiState(
        currentTemperature = "16" + "\u00B0"+"C",
        currentWeatherDescription = "Partly Cloudy",
        currentWindSpeed = "10 km/h",
        updatedTime = "04 Jul, 4:15 PM"
    ),
    onEvent: (WeatherScreenEvents) -> Unit = {},
) {
    val dropDownOptions = listOf(
        "Chandigarh" to Pair(30.7333, 76.7794),
        "Gurugram" to Pair(28.4595, 77.0266),
        "New Delhi" to Pair(28.6139, 77.2088),
        "Bangalore" to Pair(12.9629, 77.5775),
        "Mumbai" to Pair(18.9582, 72.8321),
    )
    var dropDownSelected by remember { mutableStateOf(dropDownOptions.first()) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect (dropDownSelected) {
        val latitude = dropDownSelected.second.first
        val longitude = dropDownSelected.second.second
        onEvent(WeatherScreenEvents.GetWeatherInfo(latitude, longitude))
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val focusManager = LocalFocusManager.current
            Box (
                modifier = Modifier.fillMaxWidth(),
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    Color.White.copy(alpha = 0.1f),
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.svg_location_marker_outline),
                                contentDescription = null,
                                tint = ColorWhitePure,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = dropDownSelected.first,
                            style = LocalTextStyle.current.copy(
                                fontFamily = FontFamily(getW500Font()),
                                fontWeight = FontWeight.W500,
                                fontSize = 20.sp,
                                color = ColorWhitePure
                            ),
                        )
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                .clickable { expanded = true }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = ColorWhitePure,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        containerColor = Color.White
                    ) {
                        dropDownOptions.map { it.first }.forEachIndexed { index, item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                onClick = {
                                    focusManager.clearFocus()
                                    expanded = false
                                    dropDownSelected = dropDownOptions.first { it.first == item }
                                }
                            )
                            if (index != dropDownOptions.lastIndex) {
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }

        if (state.currentTemperature != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(
                        brush = Brush.verticalGradient(listOf(Color.White.copy(alpha = 0.25f), Color.White)),
                        alpha = 0.1f
                    )
            ) {

                Image(
                    painter = painterResource(Res.drawable.png_storm_with_rain),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )

                Column (
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    state.currentTemperature?.let { temperature ->
                        Text(
                            text = temperature,
                            style = LocalTextStyle.current.copy(
                                fontFamily = FontFamily(getW500Font()),
                                fontWeight = FontWeight.W500,
                                fontSize = 48.sp,
                                color = ColorWhitePure
                            )
                        )
                    }

                    state.currentWeatherDescription?.let { weatherDescription ->
                        Text(
                            text = weatherDescription,
                            style = LocalTextStyle.current.copy(
                                color = ColorWhitePure
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                        )
                    }
                }

            }

            state.currentWindSpeed?.let { windSpeed ->
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.svg_wind),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = "Wind Speed",
                            style = LocalTextStyle.current.copy(
                                color = ColorWhitePure
                            )
                        )
                    }

                    Text(
                        text = windSpeed,
                        style = LocalTextStyle.current.copy(
                            color = ColorWhitePure
                        )
                    )
                }
            }
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Updated at " + (state.updatedTime ?: "---"),
                style = LocalTextStyle.current.copy(
                    color = ColorWhitePure
                )
            )
            IconButton (
                onClick = {
                    val latitude = dropDownSelected.second.first
                    val longitude = dropDownSelected.second.second
                    onEvent(WeatherScreenEvents.GetWeatherInfo(latitude, longitude))
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.RotateRight,
                    contentDescription = null,
                    tint = ColorWhitePure
                )
            }
        }
    }

    if (state.isLoading) {
        Loading()
    }

    if (state.error != null) {
        AlertDialogType1(
            title = "Error",
            message = state.error,
            onConfirmButtonClick = {
                onEvent(WeatherScreenEvents.DismissAlertDialog)
            },
            onDismissRequest = {
                onEvent(WeatherScreenEvents.DismissAlertDialog)
            }
        )
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    AppTheme {
        WeatherScreenContent()
    }
}