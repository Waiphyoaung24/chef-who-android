package com.example.chef_who.core.data.network.dto

import android.util.Log
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository
import com.example.chef_who.core.util.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class UsersRepositoryImpl(
    private val mealsApi: ChefWhoApi,
    private val userPreferences: UserPreferences
) : UserRepository {
    val userNameFlow: Flow<String?> = userPreferences.userNameFlow
    val userIdFlow: Flow<String?> = userPreferences.userIdFlow
    val isRegisteredFlow: Flow<Boolean> = userPreferences.isSellerFlow


    override suspend fun login(user: User): ResponseObject {
        return mealsApi.loginUser(user.email, user.password)
    }


    override suspend fun register(user: User): ResponseObject {
        val response =
            mealsApi.registerUser(user.first_name, user.last_name, user.email, user.password)

        if (response.message == "success") {
            userPreferences.saveUser(
                user.id,
                userName = user.first_name + " " + user.last_name
            )
        }
        return response
    }

    override suspend fun getUserObj(): Flow<User?> {
        return combine(
            userNameFlow,
            userIdFlow,
            isRegisteredFlow
        ) { userName, userId, isRegistered ->
            if (userId != null && userName != null) {
                User(
                    id = userId,
                    first_name = userName.split(" ")[0],
                    last_name = userName.split(" ")[1],
                    email = "", // Email is not stored; update if needed
                    password = "", // Password is not stored; update if needed
                    isSeller = isRegistered
                )
            } else {
                null // Return null if user data is incomplete
            }
        }
    }

}