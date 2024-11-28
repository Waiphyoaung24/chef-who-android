package com.example.chef_who.core.data.network.dto

import android.util.Log
import com.example.chef_who.core.domain.models.SellerProfileResponse
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository
import com.example.chef_who.core.util.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class UsersRepositoryImpl(
    private val mealsApi: ChefWhoApi,
    private val userPreferences: UserPreferences
) : UserRepository {
    val userIdFlow: Flow<String?> = userPreferences.userIdFlow
    val firstNameFlow: Flow<String?> = userPreferences.firstNameFlow

    val isSellerFlow: Flow<Boolean> = userPreferences.isSellerFlow


    override suspend fun login(user: User): ResponseObject {
        return mealsApi.loginUser(user.email, user.password)
    }


    override suspend fun register(firstName : String,lastName : String,email : String,password : String,isSeller : Boolean): ResponseObject {
       return mealsApi.registerUser(firstName,lastName,email,password,isSeller)
    }



    override suspend fun setupSellerProfile(sellerProfileResponse: SellerProfileResponse): ResponseObject {
        return mealsApi.setupSellerProfile(sellerProfileResponse)
    }

}