package com.example.assignment.ui.components.alert_dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.example.assignment.ui.theme.ColorWhitePure

@Composable
fun AlertDialogType1(
    message: String,
    onConfirmButtonClick: () -> Unit,
    title: String? = null,
    confirmButtonText: String = "Okay",
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = if (title == null) null else { { Text(title) } },
        text = { Text(message) },
        containerColor = ColorWhitePure,
        confirmButton = { TextButton(onConfirmButtonClick) { Text(confirmButtonText) } },
        properties = DialogProperties(dismissOnBackPress = false)
    )
}

@Composable
fun AlertDialogType2(
    message: String,
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    dismissButtonText: String = "Cancel",
    confirmButtonText: String = "Okay",
    title: String? = null,
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = if (title == null) null else { { Text(title) } },
        text = { Text(message) },
        containerColor = ColorWhitePure,
        confirmButton = { TextButton(onConfirmButtonClick) { Text(confirmButtonText) } },
        dismissButton = { TextButton(onDismissButtonClick) { Text(dismissButtonText) } },
        properties = DialogProperties(dismissOnBackPress = false)
    )
}