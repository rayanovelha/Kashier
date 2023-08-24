package com.rayan.kashier.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Checklist
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.components.admin.FunctionCard
import com.rayan.kashier.components.admin.RestaurantInfoCard

class AdminUiState(
    textCardProduct: String
) {

    val cardProductManagement = Pair(textCardProduct, Icons.Sharp.Checklist)

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
        FunctionCard(state.cardProductManagement,onCardClick)
    }
}

@Preview (showBackground = true)
@Composable
fun AdminScreenPreview() {
    AdminScreen(paddingValues = PaddingValues(), state = AdminUiState("Gestão de Produtos"))
}