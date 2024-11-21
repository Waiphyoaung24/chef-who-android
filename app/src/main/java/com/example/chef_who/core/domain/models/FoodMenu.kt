package com.example.chef_who.core.domain.models

data class FoodMenu(
    val country: String,
    val dsc: String,
    val id: String,
    val img: String,
    val name: String,
    val price: Double,
    val rate: Double
)