package com.example.chef_who.core.domain.usecases.auth

import android.content.SharedPreferences
import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository

class RegisterAuth(
    private val mUserRepository: UserRepository,

) {
    suspend fun createUser(firstName : String,lastName : String,email : String,password : String,isSeller : Boolean): ResponseObject {
        return mUserRepository.register(firstName,lastName,email,password,isSeller)
    }
}