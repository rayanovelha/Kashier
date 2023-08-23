package com.rayan.kashier.domain

import java.math.BigDecimal

abstract class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val category: String,
    val image: String? = null
) {
    abstract fun calculateValue(): BigDecimal
}