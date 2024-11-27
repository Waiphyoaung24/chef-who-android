package com.example.chef_who.core.domain.models

data class SellerProfileResponse(
    val imageUrl: String,
    val user_id : String,
    val menu_items: List<MenuItem>,
    val subTitle: String,
    val title: String
)