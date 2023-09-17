package com.rayan.kashier.components.products

import android.icu.math.BigDecimal
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.screen.ProductRegisterUiState
import java.text.DecimalFormat

@Composable
fun ComboProductForm(state: ProductRegisterUiState) {

    val formatter = remember {
        DecimalFormat("#.##")
    }

    Column {

        AnimatedVisibility(
            visible = state.isImageShowed,
            enter = fadeIn(),
            exit = fadeOut()
        ){
            CoilImageShower(state.image)
        }

        FormTextField(
            content = "Link da imagem" to Icons.Default.AddAPhoto,
            text = state.image,
            onTextChange = {
                state.image = it
            },
            isEnabled = true
        )

        FormTextField(
            content = "Digite o nome do combo" to Icons.Outlined.Info,
            text = state.name,
            onTextChange = { state.name = it },
            isEnabled = true
        )
        FormPriceField(
            content = "Digite o preço do combo" to Icons.Outlined.AttachMoney,
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
            content = "Digite o número do combo" to Icons.Outlined.Numbers,
            text = state.number,
            onTextChange = { state.number = it },
            isEnabled = true
        )
        FormTextField(
            content = "Digite a descrição do combo" to Icons.Outlined.Menu,
            text = state.description,
            onTextChange = { state.description = it },
            isEnabled = true
        )
        FormTextField(
            content = "Categoria: Combo" to Icons.Outlined.Lock,
            text = state.category,
            onTextChange = { state.category = it },
            isEnabled = false
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ComboProductPreview() {
    ComboProductForm(ProductRegisterUiState())
}