package com.example.chef_who.core.domain.models

data class FoodMenu(
    val id : Int,
    val name:String,
    val price:Int,
    val info:String,
    val returnPolicy:String,
    val image:String,

)