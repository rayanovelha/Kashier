package com.rayan.kashier.components.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.rayan.kashier.screen.ProductRegisterUiState

@Composable
fun ProductSelectorRadioButton(state: ProductRegisterUiState) {
    val radioOptions = state.radioOptions
    var selectedOption = state.selectedOption
    Row(Modifier.selectableGroup(), horizontalArrangement = Arrangement.Center) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = selectedOption == text,
                        onClick = { state.selectedOption = text },
                        role = Role.RadioButton
                    )
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == text,
                    onClick = { state.selectedOption = text })
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}