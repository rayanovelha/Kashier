package com.rayan.kashier.domain

import java.math.BigDecimal

class SimpleProduct(
    id: Int,
    name: String,
    description: String,
    price: BigDecimal,
    category: String,
    image: String
) : Product(id, name, description, price, category, image) {
    override fun calculateValue(): BigDecimal {
        return price
    }
}