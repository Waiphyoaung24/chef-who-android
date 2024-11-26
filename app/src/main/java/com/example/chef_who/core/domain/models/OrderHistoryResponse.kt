package com.example.chef_who.core.domain.models

data class OrderHistoryResponse(
    val order_item_id: Int,
    val menu_item_id: String,
    val menu_item_name: String,
    val menu_item_price: String,
    val menu_item_image: String,
    val quantity: String,
    val item_price: String,
    val created_at : String,
    val seller_title : String,
)