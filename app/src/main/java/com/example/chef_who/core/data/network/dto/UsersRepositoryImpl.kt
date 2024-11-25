package com.example.chef_who.core.data.network.dto

import android.util.Log
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository

class UsersRepositoryImpl(
    private val mealsApi: ChefWhoApi
) : UserRepository {
    override suspend fun login(): Boolean {
        return mealsApi.login()
    }

    override suspend fun register(user: User): ResponseObject {
        return mealsApi.registerUser(user.first_name, user.last_name, user.email, user.password)
    }

}