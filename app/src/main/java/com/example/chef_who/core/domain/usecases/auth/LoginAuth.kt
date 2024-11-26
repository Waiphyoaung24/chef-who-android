package com.example.chef_who.core.domain.usecases.auth

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository

class LoginAuth(
    private val mUserRepository: UserRepository
) {
    suspend operator fun invoke(user: User): ResponseObject {
        return mUserRepository.login(user)
    }
}