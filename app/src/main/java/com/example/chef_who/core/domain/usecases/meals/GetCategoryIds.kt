package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Dashboard

class GetCategoryIds(
    private val mealsRepository: MealsRepository
) {
    suspend operator fun invoke(): List<Category> {
        return mealsRepository.getAllCategoryIds()
    }
}