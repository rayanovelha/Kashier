@file:OptIn(ExperimentalMaterial3Api::class)

package com.rayan.kashier

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayan.kashier.components.StandardTopAppBar
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.sample.sampleCombo
import com.rayan.kashier.sample.sampleProduct
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

class HomeUiState {
    val cardCashier = Triple("Modo Caixa", Icons.Filled.ShoppingCart, R.drawable.card_cashier)
    val cardAdmin = Triple("Modo Admin", Icons.Filled.AccountCircle, R.drawable.card_admin)
}

@Composable
fun HomeCard(content: Triple<String, ImageVector, Int>, onCardClick: () -> Unit = {}) {

    val maxWidth = 300
    val maxHeight = 180
    val padding = 16
    val text = content.first
    val icon = content.second
    val image = content.third

    ElevatedCard(
        modifier = Modifier
            .size(maxWidth.dp, maxHeight.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(),
        onClick = { onCardClick() }
    ) {
        Column(
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(maxWidth.dp, (maxHeight * 0.70).dp),
                contentScale = ContentScale.Crop
            )
            Box(
                Modifier
                    .width(maxWidth.dp)
                    .height((maxHeight * 0.45).dp)
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = padding.dp)
                        .offset(0.dp, -(0.15 * maxHeight).dp)
                        .align(Alignment.CenterEnd)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(
                                    Color.White,
                                    Color.LightGray
                                )
                            )
                        )
                        .size(40.dp),
                    imageVector = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Inside
                )
                Text(
                    text = text,
                    fontFamily = FontFamily(Font(R.font.firasans_semibold)),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = padding.dp)
                )
            }

        }

    }
}

@Composable
fun Home(
    state: HomeUiState,
    onAdminCardClick: () -> Unit = {},
    onCashierCardClick: () -> Unit = {}
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(32.dp))
        HomeCard(state.cardCashier,onCashierCardClick)
        Spacer(modifier = Modifier.size(32.dp))
        HomeCard(state.cardAdmin, onAdminCardClick)

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
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
        {
            Home(state, onAdminCardClick, onCashierCardClick)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScaffold(HomeUiState())
}