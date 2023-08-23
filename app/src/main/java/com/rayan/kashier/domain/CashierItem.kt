package com.rayan.kashier.domain

class CashierItem (
    val item: Product,
    var qty: Int = 0
){
    fun addQty(){
        this.qty++
    }
}