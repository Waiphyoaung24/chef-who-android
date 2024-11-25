package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.repository.MealsRepository

class CreateOrder(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(order : Order): ResponseObject {
        return mealsRepository.createOrder(order)
    }

}