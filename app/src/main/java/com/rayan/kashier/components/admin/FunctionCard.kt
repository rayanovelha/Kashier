package com.rayan.kashier.components.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Checklist
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayan.kashier.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FunctionCard(
    content: Pair<String, ImageVector>,
    onCardClick: () -> Unit = {}
) {

    val padding = 16
    val text = content.first
    val icon = content.second

    Card(
        onClick = { onCardClick() },
        modifier = Modifier.wrapContentSize(),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp,Color.LightGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(padding.dp)
        ) {
            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.firasans_semibold)),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(end = padding.dp)
            )
            Image(
                modifier = Modifier
                    .padding(start = padding.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color.White,
                                Color.LightGray
                            )
                        )
                    ),
                imageVector = icon,
                contentDescription = null,
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FunctionCardPreview() {
    FunctionCard(
        content = Pair("Gestão de Produtos", Icons.Sharp.Checklist),
        onCardClick = { null }
    )
}