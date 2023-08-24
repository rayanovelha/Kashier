package com.rayan.kashier.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.HeadlineText
import com.rayan.kashier.components.TitleText
import com.rayan.kashier.components.products.ProductListItem
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant

class ProductManagementUiState {

}

@Composable
fun ProductManagementScreen(
    products: List<Product>,
    state: ProductManagementUiState
) {
    Column {
        TitleText(
            text = "Produtos cadastrados:",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp, end = 8.dp, start = 16.dp)
        )
        LazyColumn {
            for (c in Restaurant.listCategories()) {
                item {
                    HeadlineText(text = c, modifier = Modifier.padding(16.dp))
                    val productsFiltered = products.filter {
                        it.category == c
                    }
                    productsFiltered.forEach { product ->
                        Spacer(modifier = Modifier.size(2.dp))
                        ProductListItem(product = product)
                    }
                }
            }
        }

        Divider(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp))
    }
}