package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.repository.MealsRepository

class UpdateOrderStatus(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(orderId: String, orderStatus: String): ResponseObject {
        return mealsRepository.updateOrderStatus(orderId, orderStatus)
    }
}