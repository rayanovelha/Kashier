package com.rayan.kashier.domain

import androidx.compose.runtime.mutableStateListOf

object Restaurant {
    val id: Int = 0
    val nome: String = "Restaurante de Sushi"
    val logo: String = "https://images.pexels.com/photos/15197997/pexels-photo-15197997/free-photo-of-aperitivo-lanche-petisco-fotografia-de-alimentos.jpeg"

    private val products = mutableStateListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun products() = products.toList().sortedBy { it.id }

    /*fun searchProductsList (search: String): List<Product> {
        return productsList.filter {
            it.name.contains(search) || it.id == search.toInt()
        }
    }*/

}