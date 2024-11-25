package com.example.chef_who.core.domain.usecases.meals

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Food
import kotlinx.coroutines.flow.Flow

class SearchMeals(
    private val mealsRepository: MealsRepository
) {

    suspend operator fun invoke(keyword: String):List<Food> {
        return mealsRepository.search(keyword)
    }
}