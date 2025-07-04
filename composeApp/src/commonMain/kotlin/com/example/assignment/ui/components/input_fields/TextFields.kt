package com.example.assignment.ui.components.input_fields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.ui.theme.Color171745PrimaryBlue
import com.example.assignment.ui.theme.ColorDDDDDD
import com.example.assignment.ui.theme.getW500Font
import com.example.assignment.ui.theme.getW600Font

@Composable
private fun textStyle() = LocalTextStyle.current.copy(
    fontFamily = FontFamily(getW500Font()),
    fontWeight = FontWeight.W500,
    fontSize = 14.sp,
    lineHeight = 18.sp,
    color = Color171745PrimaryBlue,
)

@Composable
private fun labelTextStyle() = LocalTextStyle.current.copy(
    fontFamily = FontFamily(getW600Font()),
    fontWeight = FontWeight.W600,
    fontSize = 12.sp,
    lineHeight = 14.63.sp,
    color = Color171745PrimaryBlue,
)

@Composable
private fun DefaultInputFieldTextStyle() = textStyle()

@Composable
private fun DefaultInputFieldLabelTextStyle() = labelTextStyle()

@Composable
private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color171745PrimaryBlue,
    unfocusedBorderColor = ColorDDDDDD,
    focusedTextColor = Color171745PrimaryBlue,
    unfocusedTextColor = Color171745PrimaryBlue,
)

@Composable
fun TextFieldGeneral(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String,

    textStyle: TextStyle = DefaultInputFieldTextStyle(),
    labelStyle: TextStyle = DefaultInputFieldLabelTextStyle(),
    colors: TextFieldColors = textFieldColors(),
    modifier: Modifier = Modifier,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    shape: Shape = RoundedCornerShape(5.dp),

    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    minLines: Int = 1,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = { Text(text = label, style = labelStyle) },
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        shape = shape,
        colors = colors,
    )
}