package com.example.chef_who.core.domain.repository

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.SellerProfileResponse
import com.example.chef_who.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(user: User) : ResponseObject
    suspend fun register(firstName : String,lastName : String,email : String,password : String,isSeller : Boolean) : ResponseObject
    suspend fun setupSellerProfile(sellerProfileResponse: SellerProfileResponse) : ResponseObject


}