package com.example.assignment.ui.components.bottomsheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.assignment.ui.theme.ColorWhitePure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheet(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true) { it != SheetValue.Hidden },
    containerColor: Color = ColorWhitePure,
    content: @Composable ColumnScope.() -> Unit
) {
    if (isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            containerColor = containerColor,
            dragHandle = null,
            properties = ModalBottomSheetProperties(shouldDismissOnBackPress = false),
            content = content
        )
    }
}