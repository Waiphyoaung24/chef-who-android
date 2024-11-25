package com.example.chef_who.core.domain.models

data class Order(
    val userId : String,
    val cartItems : List<Cart>
)
