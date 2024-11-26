package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.repository.MealsRepository

class GetOrderHistory(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(userId :String): List<OrderHistoryResponse> {
        return mealsRepository.getOrderHistoryList(userId)
    }
}