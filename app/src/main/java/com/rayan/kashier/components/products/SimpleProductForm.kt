package com.rayan.kashier.components.products

import android.icu.math.BigDecimal
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.rayan.kashier.screen.ProductRegisterUiState
import java.text.DecimalFormat

@Composable
fun SimpleProductForm(state: ProductRegisterUiState) {

    val formatter = remember {
        DecimalFormat("#.##")
    }

    Column {
        FormTextField(
            content = "Digite o nome do produto" to Icons.Outlined.Info,
            text = state.name,
            onTextChange = { state.name = it },
            isEnabled = true
        )
        FormPriceField(
            content = "Digite o preço do produto" to Icons.Outlined.AttachMoney,
            text = state.price,
            onTextChange = {
                try {
                    state.price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        state.price = it
                    }
                }
            },
        )
        FormNumberField(
            content = "Digite o número do produto" to Icons.Outlined.Numbers,
            text = state.number,
            onTextChange = { state.number = it },
            isEnabled = true
        )
        FormTextField(
            content = "Digite a descrição do produto" to Icons.Outlined.Menu,
            text = state.description,
            onTextChange = { state.description = it },
            isEnabled = true
        )
        FormTextField(
            content = "Defina a categoria do produto" to Icons.Outlined.Category,
            text = state.category,
            onTextChange = { state.category = it },
            isEnabled = true
        )
    }
}