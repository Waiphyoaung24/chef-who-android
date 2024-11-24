package com.example.chef_who.core.domain.usecases.auth

import android.content.SharedPreferences
import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository

class RegisterAuth(
    private val mUserRepository: UserRepository,

) {
    var isUserRegister : Boolean = false
    suspend fun createUser(user: User): Boolean {
        if(mUserRepository.register(user).messasge == "success"){
            isUserRegister = true
        }
        return isUserRegister
    }



}