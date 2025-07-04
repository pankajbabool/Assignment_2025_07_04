package com.example.assignment.ui.components.date_time_pickers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.ui.theme.Color171745PrimaryBlue
import com.example.assignment.ui.theme.ColorWhitePure
import com.example.assignment.ui.theme.getW700Font
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val pickerState = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }.let {
        rememberTimePickerState(it.hour, it.minute)
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Column (
                modifier = Modifier
                   .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Time",
                    style = LocalTextStyle.current.copy(
                        fontFamily = FontFamily(getW700Font()),
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        color = Color171745PrimaryBlue,
                    )
                )
                TimePicker(pickerState, layoutType = TimePickerLayoutType.Vertical)
            }
        },
        containerColor = ColorWhitePure,
        confirmButton = { TextButton({
            val formattedTime = LocalTime(pickerState.hour, pickerState.minute).toString()
            onTimeSelected(formattedTime)
            onDismiss()
        }) { Text("Confirm") } },
        dismissButton = { TextButton(onDismiss) { Text("Cancel") } }
    )
}