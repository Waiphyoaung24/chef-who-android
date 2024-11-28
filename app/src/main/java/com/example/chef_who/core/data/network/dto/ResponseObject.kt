package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.domain.models.User
import com.example.chef_who.customer.domain.Food

data class ResponseObject(
    val message : String,
    val user : User?=null,
    val foodList : List<Food?>
)
