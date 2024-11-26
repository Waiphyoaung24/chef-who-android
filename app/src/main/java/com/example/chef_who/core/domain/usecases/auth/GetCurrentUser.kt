package com.example.chef_who.core.domain.usecases.auth

import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUser(private val userRepository: UserRepository) {

    suspend operator fun invoke():Flow<User?> {
        return userRepository.getUserObj()
    }
}