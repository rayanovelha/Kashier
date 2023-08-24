@file:OptIn(ExperimentalMaterial3Api::class)

package com.rayan.kashier

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.components.StandardTopAppBar
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.sample.sampleCombo
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.screen.HomeScreen
import com.rayan.kashier.screen.HomeUiState
import com.rayan.kashier.ui.theme.KashierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sampleProducts = sampleCombo + sampleProduct
        sampleProducts.forEach {
            Restaurant.addProduct(it)
        }


        val mainToAdmin = Intent(this, AdminActivity::class.java)
        val mainToCashier = Intent(this, ProductRegisterActivity::class.java)
        val homeUiState = HomeUiState()

        setContent {
            KashierTheme {
                Surface {
                    HomeScaffold(
                        homeUiState,
                        onAdminCardClick = {
                            startActivity(mainToAdmin)
                        },
                        onCashierCardClick = {
                            startActivity(mainToCashier)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScaffold(
    state: HomeUiState,
    onAdminCardClick: () -> Unit = {},
    onCashierCardClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { StandardTopAppBar(text = "Tela Inicial") }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        )
        {
            HomeScreen(state, onAdminCardClick, onCashierCardClick)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScaffold(HomeUiState())
}