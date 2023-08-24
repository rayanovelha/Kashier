package com.rayan.kashier.components.products

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FormNumberField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit,
    isEnabled: Boolean
) {
    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        enabled = isEnabled
    )
}

@Composable
fun FormPriceField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit
) {

    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun FormTextField(
    content: Pair<String, ImageVector>,
    text: String,
    onTextChange: (String) -> Unit,
    isEnabled: Boolean
) {
    val hint = content.first
    val icon = content.second

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        },
        label = {
            Text(hint)
        },
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.LightGray,
            disabledTextColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences
        ),
        enabled = isEnabled
    )
}