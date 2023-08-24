package com.rayan.kashier.components.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rayan.kashier.R
import com.rayan.kashier.domain.Product
import com.rayan.kashier.extensions.toBrazilianCurrency
import java.util.Locale


@Composable
fun ProductListItem(product: Product) {
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