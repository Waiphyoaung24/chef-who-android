package com.example.chef_who.core.domain.usecases.meals

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Dashboard
import kotlinx.coroutines.flow.Flow

class GetHomeType(
    private val mealsRepository: MealsRepository
) {
     suspend operator fun invoke(): Dashboard {
        return mealsRepository.getHomeType()
    }
}