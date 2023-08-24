package com.rayan.kashier.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rayan.kashier.R
import com.rayan.kashier.components.home.HomeCard

class HomeUiState {
    val cardCashier = Triple("Modo Caixa", Icons.Filled.ShoppingCart, R.drawable.card_cashier)
    val cardAdmin = Triple("Modo Admin", Icons.Filled.AccountCircle, R.drawable.card_admin)
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAdminCardClick: () -> Unit = {},
    onCashierCardClick: () -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        HomeCard(state.cardCashier,onCashierCardClick,300,180)
        Spacer(modifier = Modifier.size(32.dp))
        HomeCard(state.cardAdmin, onAdminCardClick,300,180)

    }

}