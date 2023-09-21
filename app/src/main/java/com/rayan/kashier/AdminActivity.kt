package com.rayan.kashier

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayan.kashier.components.TextAndBackIconTopAppBar
import com.rayan.kashier.screen.AdminScreen
import com.rayan.kashier.screen.AdminUiState
import com.rayan.kashier.ui.theme.KashierTheme

class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adminUiState = AdminUiState(
            getString(R.string.title_activity_product_management),
            getString(R.string.title_activity_finance_management),
            getString(R.string.title_activity_restaurant_management)
        )

        val adminToProductManagement = Intent(this, ProductManagementActivity::class.java)

        setContent {
            KashierTheme {
                AdminScaffold(
                    onBackClick = { finish() },
                    state = adminUiState,
                    onCard1Click = { startActivity(adminToProductManagement) })
            }
        }
    }
}

@Composable
fun AdminScaffold(
    onBackClick: () -> Unit, state: AdminUiState, onCard1Click: () -> Unit
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
                AdminScreen(paddingValues, state, onCard1Click)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminScaffoldPreview() {
    val state =
        remember { AdminUiState(
            "Gestão de Produtos",
            "Financeiro",
            "Dados do Restaurante"
        ) }
    AdminScaffold({ null }, state, { null })
}