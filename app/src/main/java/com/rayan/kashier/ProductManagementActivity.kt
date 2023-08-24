package com.rayan.kashier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.components.StandardTopAppBar
import com.rayan.kashier.ui.theme.KashierTheme

class ProductManagementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KashierTheme {
                ProductManagementScaffold()
            }
        }
    }
}

@Composable
fun ProductManagementScaffold() {
    Scaffold(
        topBar = { StandardTopAppBar(text = "Gestão de Produtos") }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {

        }

    }
}