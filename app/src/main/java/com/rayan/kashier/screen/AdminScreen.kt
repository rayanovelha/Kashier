package com.rayan.kashier.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.admin.FunctionCard
import com.rayan.kashier.components.admin.RestaurantInfoCard

class AdminUiState(
    textCardProductManagement: String,
    textCardFinanceManagement: String,
    textCardRestaurantManagement: String
) {

    val cardProductManagement = Pair(textCardProductManagement, Icons.Outlined.Checklist)
    val cardFinanceManagement = Pair(textCardFinanceManagement, Icons.Outlined.Payments)
    val cardRestaurantManagement = Pair(textCardRestaurantManagement, Icons.Outlined.Storefront)

}

@Composable
fun AdminScreen(
    paddingValues: PaddingValues,
    state: AdminUiState,
    onCardClick: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RestaurantInfoCard()
        Spacer(modifier = Modifier.padding(paddingValues))
        FunctionCard(state.cardRestaurantManagement,onCardClick)
        Spacer(modifier = Modifier.padding(16.dp))
        FunctionCard(state.cardProductManagement,onCardClick)
        Spacer(modifier = Modifier.padding(16.dp))
        FunctionCard(state.cardFinanceManagement,onCardClick)
    }
}

@Preview (showBackground = true)
@Composable
fun AdminScreenPreview() {
    AdminScreen(
        paddingValues = PaddingValues(),
        state = AdminUiState(
            "Gestão de Produtos",
            "Financeiro",
            "Dados do Restaurante"
        )
    )
}