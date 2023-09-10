package com.rayan.kashier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rayan.kashier.ui.theme.KashierTheme


class CashierActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KashierTheme {

            }
        }
    }
}