package com.example.chef_who.core.domain.models

import androidx.room.Entity

@Entity
data class Cart(
    val menu_item_id : Int,
    val img : String,
    val status : String,
    val name: String,
    val price: Double,
    val quantity: Int
)
