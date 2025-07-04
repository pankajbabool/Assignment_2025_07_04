package com.example.assignment.ui.components.dropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.assignment.ui.theme.ColorWhitePure
import com.example.assignment.ui.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableDropDownOptions(
    modifier: Modifier,
    value: String,
    list: List<String>,
    label: String,
    isEditable: Boolean = true,
    containerColor: Color = ColorWhitePure,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var expanded by remember { mutableStateOf(false) }
    Box (modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
//            TextFieldGeneral(
//                value = value,
//                label = label,
//                readOnly = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true),
//            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = containerColor
            ) {
                list.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        onClick = {
                            focusManager.clearFocus()
                            expanded = false
                            onValueChange(item)
                        }
                    )
                    if (index != list.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Box (
            modifier = Modifier
                .matchParentSize()
                .noRippleClickable { if (isEditable) expanded = true }
        )
    }
}