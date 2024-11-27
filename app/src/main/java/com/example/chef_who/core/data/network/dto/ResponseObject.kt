package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.domain.models.User

data class ResponseObject(
    val message : String,
    val user : User?
)
