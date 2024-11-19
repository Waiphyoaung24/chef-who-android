package com.example.chef_who.core.domain.usecases.meals

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMeals(
    private val mealsRepository: MealsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return mealsRepository.getMeals(sources)
    }

}