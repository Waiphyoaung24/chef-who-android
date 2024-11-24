package com.example.chef_who.core.domain.usecases.meals

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class SearchMeals(
    private val mealsRepository: MealsRepository
) {

    operator fun invoke(searchQuery: String): Flow<PagingData<Category>> {
        return mealsRepository.searchCategory(searchQuery)
    }
}