package com.example.chef_who.core.domain.usecases.auth

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.SellerProfileResponse
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.repository.UserRepository

class SetUpSellerProfile(private val userRepository: UserRepository) {
    suspend operator fun invoke(sellerProfileResponse: SellerProfileResponse): ResponseObject {
        return userRepository.setupSellerProfile(sellerProfileResponse)
    }
}