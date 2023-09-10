package com.rayan.kashier

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.components.TextAndBackIconTopAppBar
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.sample.sampleCombo
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.screen.ProductManagementScreen
import com.rayan.kashier.screen.ProductManagementUiState
import com.rayan.kashier.ui.theme.KashierTheme

class ProductManagementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productManagementUiState = ProductManagementUiState()
        val toProductRegister = Intent(this, ProductRegisterActivity::class.java)

        setContent {
            KashierTheme {
                ProductManagementScaffold(products = Restaurant.listProducts(),
                    uiState = productManagementUiState,
                    onBackClick = { finish() },
                    onPlusClick = { startActivity(toProductRegister) }
                )
            }
        }
    }
}

@Composable
fun ProductManagementScaffold(
    products: List<Product>, uiState: ProductManagementUiState, onBackClick: () -> Unit = {}, onPlusClick: () -> Unit = {}
) {
    Scaffold(topBar = {
        TextAndBackIconTopAppBar(
            text = "Gestão de Produtos", onBackClick = onBackClick
        )
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onPlusClick
            ){
                Icon(Icons.Default.Add,null)
            }
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            ProductManagementScreen(products = products, state = uiState)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductManagementScaffoldPreview() {
    val uiState = remember { ProductManagementUiState() }
    ProductManagementScaffold(sampleProduct + sampleCombo, uiState, { null }, { null })
}