package com.rayan.kashier.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayan.kashier.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextAndBackIconTopAppBar(text: String, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onBackground),
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "voltar"
                )
            }
        },
        title = { Text(text, fontFamily = FontFamily(Font(R.font.firasans_bold))) }
    )
}

@Preview
@Composable
fun TextAndBackIconTopAppBarPreview() {
    TextAndBackIconTopAppBar(text = "Preview", { null })
}