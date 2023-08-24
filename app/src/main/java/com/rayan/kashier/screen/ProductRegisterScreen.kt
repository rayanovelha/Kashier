package com.rayan.kashier.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.products.CoilImageShower
import com.rayan.kashier.components.products.ComboProductForm
import com.rayan.kashier.components.products.ProductSelectorRadioButton
import com.rayan.kashier.components.products.SimpleProductForm

class ProductRegisterUiState {
    val radioOptions = listOf("Produto", "Combo")
    var selectedOption by mutableStateOf(radioOptions[0])

    var name by mutableStateOf("")
    var price by mutableStateOf("")
    var number by mutableStateOf("")
    var description by mutableStateOf("")
    var category by mutableStateOf("")
    var image by mutableStateOf("https://images.pexels.com/photos/14469273/pexels-photo-14469273.jpeg")
}

@Composable
fun ProductRegisterScreen(state: ProductRegisterUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            //.padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        Spacer(modifier = Modifier)

        ProductSelectorRadioButton(state)

        CoilImageShower(state.image)

        Spacer(modifier = Modifier.size(16.dp))

        val selected = (state.selectedOption == state.radioOptions[1])

        Crossfade(targetState = selected, label = "") {
            when (it){
                true ->
                    ComboProductForm(state = state)
                false ->
                    SimpleProductForm(state = state)
            }
        }

        Spacer(modifier = Modifier.size(64.dp))
    }
}