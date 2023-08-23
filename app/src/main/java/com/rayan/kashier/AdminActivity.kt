@file:OptIn(ExperimentalMaterial3Api::class)

package com.rayan.kashier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rayan.kashier.components.TextAndBackIconTopAppBar
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.Restaurant
import com.rayan.kashier.extensions.toBrazilianCurrency
import com.rayan.kashier.sample.sampleCombo
import com.rayan.kashier.sample.sampleProduct
import com.rayan.kashier.ui.theme.KashierTheme
import java.util.Locale

class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KashierTheme {
                ProductsAdminScaffold(Restaurant.products(), onBackClick = { finish() })
            }
        }
    }
}

@Composable
fun RestaurantInfoCard() {

    val height = 100
    val width = 250

    Card(
        onClick = { /* TODO */ }, modifier = Modifier.wrapContentSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(Restaurant.logo)
                    .crossfade(true).build(),
                modifier = Modifier
                    .width((width * 0.3).dp)
                    .height((height * 0.7).dp)
                    .clip(CircleShape)
                    .shadow(4.dp),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = Restaurant.nome,
                    fontFamily = FontFamily(Font(R.font.firasans_bold)),
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.End
                )
                Text(
                    text = "status",
                    fontFamily = FontFamily.Monospace,
                    color = Color.Gray,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun ProductsAdminScreen(
    products: List<Product>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        RestaurantInfoCard()
        Divider(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp))
        Text(
            text = "Produtos cadastrados:",
            fontFamily = FontFamily(Font(R.font.firasans_medium)),
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )
        products.forEach { product ->
            Spacer(modifier = Modifier.size(2.dp))
            ListItem(
                headlineContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.id.toString(),
                            fontStyle = FontStyle.Italic,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .padding(end = 4.dp)

                        )
                        Text(
                            text = product.name,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                supportingContent = {
                    Text(
                        text = product.description,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        maxLines = 2,
                        lineHeight = 12.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                trailingContent = {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = product.category.lowercase()
                                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = product.calculateValue().toBrazilianCurrency(),
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                leadingContent = {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(product.image)
                            .crossfade(true).build(),
                        modifier = Modifier
                            .size(56.dp)
                            .padding(2.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(4.dp),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                },
            )
        }
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
                ProductsAdminScreen(products)
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