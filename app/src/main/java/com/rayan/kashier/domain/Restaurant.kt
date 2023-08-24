package com.rayan.kashier.domain

import androidx.compose.runtime.mutableStateListOf

object Restaurant {
    const val id: Int = 0
    const val nome: String = "Restaurante de Sushi"
    const val logo: String = "https://images.pexels.com/photos/15197997/pexels-photo-15197997/free-photo-of-aperitivo-lanche-petisco-fotografia-de-alimentos.jpeg"

    private val products = mutableStateListOf<Product>()
    private val categories = mutableStateListOf<String>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun listProducts() = products.toList().sortedBy { it.id }

    fun listCategories(): List<String> {
        products.forEach {
            if (!categories.contains(it.category))
                categories.add(it.category)
        }
        return categories.toList().sortedBy { it }
    }

    /*fun searchProductsList (search: String): List<Product> {
        return productsList.filter {
            it.name.contains(search) || it.id == search.toInt()
        }
    }*/

}