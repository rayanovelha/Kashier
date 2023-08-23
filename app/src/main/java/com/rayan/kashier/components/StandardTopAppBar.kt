package com.rayan.kashier.components

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.rayan.kashier.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTopAppBar(text: String){
    CenterAlignedTopAppBar(
        title = { Text(text, fontFamily = FontFamily(Font(R.font.firasans_bold))) },
        Modifier.background(MaterialTheme.colorScheme.onBackground)
    )
}

@Preview
@Composable
fun StandardTopAppBarPreview() {
    StandardTopAppBar(text = "Preview")
}