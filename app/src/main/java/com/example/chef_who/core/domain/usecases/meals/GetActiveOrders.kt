package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.repository.MealsRepository

class GetActiveOrders(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(sellerId :String): List<OrderActiveResponse> {
        return mealsRepository.getActiveOrders(sellerId)
    }
}