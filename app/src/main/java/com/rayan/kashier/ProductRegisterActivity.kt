package com.rayan.kashier

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.StandardTopAppBar
import com.rayan.kashier.components.products.ExtendedFABWithText
import com.rayan.kashier.components.products.FABWithoutText
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.screen.ProductRegisterScreen
import com.rayan.kashier.screen.ProductRegisterUiState
import com.rayan.kashier.ui.theme.KashierTheme

class ProductRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productRegisterUiState = ProductRegisterUiState()

        setContent {
            KashierTheme {
                Surface {
                    ProductRegisterScaffold(
                        onSaveClick = { product ->
                            Restaurant.addProduct(product)
                            Log.i("PRODUTO CADASTRADO", Restaurant.listProducts().toString())
                            finish()
                        },
                        onClick = {
                            finish()
                        },
                        state = productRegisterUiState,
                        title = getString(R.string.title_activity_product_register)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductRegisterScaffold(
    onSaveClick: (Product) -> Unit,
    onClick: () -> Unit,
    state: ProductRegisterUiState,
    title: String
) {
    Scaffold(
        floatingActionButton = {
            Row(Modifier.padding(16.dp)) {
                ExtendedFABWithText(
                    text = "Confirmar",
                    icon = Icons.Outlined.Check,
                    onSaveClick,
                    state
                )
                FABWithoutText(icon = Icons.Outlined.Close, onClick)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        topBar = { StandardTopAppBar(title) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            ProductRegisterScreen(state)
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenPreview() {
    val state = remember { ProductRegisterUiState() }
    ProductRegisterScaffold(
        onSaveClick = { product ->
            Restaurant.addProduct(product)
        },
        onClick = {
        },
        state,
        "Gestão de Produtos"
    )
}