package com.yisokheng.finalapp.modelapp

data class CartMD(
    val items: List<CartItem>,
    val totalPrice: Double,
    var fullName: String = "",
    var tel: String = "",
    var address: String = ""
)
