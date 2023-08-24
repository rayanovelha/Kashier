package com.rayan.kashier.components.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rayan.kashier.R
import com.rayan.kashier.domain.Restaurant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantInfoCard() {

    val height = 100
    val width = 250

    Card(
        onClick = { /* TODO */ },
        enabled = false,
        modifier = Modifier.wrapContentSize()
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