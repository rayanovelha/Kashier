package com.rayan.kashier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.HeadlineText
import com.rayan.kashier.components.TextAndBackIconTopAppBar
import com.rayan.kashier.components.TitleText
import com.rayan.kashier.components.admin.RestaurantInfoCard
import com.rayan.kashier.components.products.ProductListItem
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.sample.sampleCombo
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.ui.theme.KashierTheme

class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KashierTheme {
                ProductsAdminScaffold(Restaurant.listProducts(), onBackClick = { finish() })
            }
        }
    }
}

@Composable
fun ProductsAdminScreen(
    products: List<Product>,
    paddingValues: PaddingValues
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RestaurantInfoCard()
        Divider(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp))
        TitleText(text = "Produtos cadastrados:", modifier = Modifier.align(Alignment.Start).padding(top = 16.dp, end = 8.dp, start = 16.dp))
        LazyColumn {
            for (c in Restaurant.listCategories()){
                item{
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

@Composable
fun ProductsAdminScaffold(
    products: List<Product>, onBackClick: () -> Unit
) {
    Scaffold(topBar = { TextAndBackIconTopAppBar(text = "Admin", onBackClick) }) { paddingValues ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                ProductsAdminScreen(products, paddingValues)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantInfoCardPreview() {
    RestaurantInfoCard()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductsAdminScaffoldPreview() {
    ProductsAdminScaffold(sampleProduct + sampleCombo, { null })
}