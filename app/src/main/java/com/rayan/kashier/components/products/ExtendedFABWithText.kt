package com.rayan.kashier.components.products

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.rayan.kashier.domain.Combo
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.SimpleProduct
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.screen.ProductRegisterUiState
import java.math.BigDecimal

@Composable
fun ExtendedFABWithText(
    text: String,
    icon: ImageVector,
    onSaveClick: (Product) -> Unit = {},
    state: ProductRegisterUiState
) {
    ExtendedFloatingActionButton(
        onClick = {
            val convertedPrice = try {
                BigDecimal(state.price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }

            lateinit var product: Product

            if (state.selectedOption == state.radioOptions[0]) {
                product = SimpleProduct(
                    id = state.number.toInt(),
                    name = state.name,
                    description = state.description,
                    price = convertedPrice,
                    category = state.category,
                    image = state.image
                )
            } else if (state.selectedOption == state.radioOptions[1]) {
                product = Combo(
                    id = state.number.toInt(),
                    name = state.name,
                    description = state.description,
                    price = convertedPrice,
                    products = sampleProduct,
                    image = state.image
                )
            }
            onSaveClick(product)
        },
        modifier = Modifier.padding(start = 4.dp),
        text = { Text(text) },
        icon = { Icon(imageVector = icon, contentDescription = null) })
}