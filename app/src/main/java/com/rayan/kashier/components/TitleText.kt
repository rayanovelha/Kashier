package com.rayan.kashier.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayan.kashier.R

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        fontFamily = FontFamily(Font(R.font.firasans_medium)),
        fontSize = 19.sp,
        modifier = modifier
    )
}