package com.rayan.kashier.sample

import com.rayan.kashier.domain.Combo
import com.rayan.kashier.domain.Product
import com.rayan.kashier.domain.SimpleProduct
import java.math.BigDecimal

val sampleProduct = listOf<Product>(
    SimpleProduct(
        1,
        "Bandeja de Sushi 20 un.",
        "10 uramaki tilápia + 10 shakemaki salmão",
        BigDecimal(30.0),
        "Bandejas",
        "https://images.pexels.com/photos/5900773/pexels-photo-5900773.jpeg"
    ),

    SimpleProduct(
        2,
        "Saquê",
        "Garrafa de Saquê 250ml",
        BigDecimal(12.0),
        "Bebidas",
        "https://images.pexels.com/photos/11661146/pexels-photo-11661146.jpeg"
    ),

    SimpleProduct(
        3,
        "Barca de Sushi 50 un.",
        "10 uramaki + 10 shakemaki + 10 hossomaki + 20 hot roll + 10 nigiri",
        BigDecimal(75.0),
        "Barcas",
        "https://images.pexels.com/photos/11470589/pexels-photo-11470589.jpeg"
    )
)

val sampleCombo = listOf<Product>(
    Combo(
        4,
        "Combo Individual",
        "Bandeja de Sushi 20 un. + 1 Saquê",
        BigDecimal(100.0),
        sampleProduct,
        "https://images.pexels.com/photos/17178444/pexels-photo-17178444/free-photo-of-fundo-preto-tigela-bowl-bacia.jpeg"
    ),

    Combo(
        5,
        "Combo Casal",
        "Barca de Sushi 50 un. + 2 Saquê",
        BigDecimal(120.0),
        sampleProduct.reversed(),
        "https://images.pexels.com/photos/5616153/pexels-photo-5616153.jpeg"
    )
)
