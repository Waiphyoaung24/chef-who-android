package com.example.chef_who.core.domain.repository

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.User

interface UserRepository {
    suspend fun login() : Boolean
    suspend fun register(user : User) : ResponseObject
}