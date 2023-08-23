package com.rayan.kashier.domain

import java.math.BigDecimal

class Combo(
    id: Int,
    name: String,
    description: String,
    price: BigDecimal,
    products: List<Product>,
    image: String
) : Product(id, name, description, price, "Combo", image) {
    private val totalPrice = products.sumOf{ it.price }
    override fun calculateValue(): BigDecimal {
        return totalPrice
    }
}