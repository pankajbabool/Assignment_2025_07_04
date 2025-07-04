package com.example.assignment.ui.components.date_time_pickers

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.assignment.extensions.convertMillisToDateAsDDMMYYYY
import com.example.assignment.extensions.getStartOfTodayMillis

enum class DateAllow {
    All,
    PastOnly,
    FutureOnly,
    TodayAndPast,
    TodayAndFuture
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit,
    dateAllow: DateAllow = DateAllow.All
) {
    val currentTimeMillis = remember { getStartOfTodayMillis() }
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return when (dateAllow) {
                    DateAllow.All -> true
                    DateAllow.PastOnly -> utcTimeMillis < currentTimeMillis
                    DateAllow.FutureOnly -> utcTimeMillis > currentTimeMillis
                    DateAllow.TodayAndPast -> utcTimeMillis <= currentTimeMillis
                    DateAllow.TodayAndFuture -> utcTimeMillis >= currentTimeMillis
                }
            }
        }
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        content = { DatePicker(datePickerState) },
        dismissButton = { OutlinedButton({ onDismiss() }) { Text("Cancel") } },
        confirmButton = {
            Button({
                val millis = datePickerState.selectedDateMillis
                val selectedDate = if (millis != null) convertMillisToDateAsDDMMYYYY(millis) else ""
                onDateSelected(selectedDate)
                onDismiss()
            }) {
                Text("OK")
            }
        }
    )
}