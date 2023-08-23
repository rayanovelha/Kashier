package com.rayan.kashier.lists

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Numbers

val productRegisterForms = mapOf(
    "Digite o nome do produto" to Icons.Outlined.Info,
    "Digite o preço do produto" to Icons.Outlined.AttachMoney,
    "Digite o número do produto" to Icons.Outlined.Numbers,
    "Digite a descrição do produto" to Icons.Outlined.Menu,
    "Defina a categoria do produto" to Icons.Outlined.Category
).toList()

val comboRegisterForms = mapOf(
    "Digite o nome do combo" to Icons.Outlined.Info,
    "Digite o preço do combo" to Icons.Outlined.AttachMoney,
    "Digite o número do combo" to Icons.Outlined.Numbers,
    "Digite o conteúdo do combo" to Icons.Outlined.Menu,
).toList()

