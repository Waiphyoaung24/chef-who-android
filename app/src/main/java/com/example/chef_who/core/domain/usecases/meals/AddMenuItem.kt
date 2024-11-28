package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Food

class AddMenuItem(private val mealsRepository: MealsRepository) {
    suspend operator fun invoke(sellerId: String, food: FoodMenu): ResponseObject {
        return mealsRepository.addMenuItemToCloud(sellerId, food)
    }
}