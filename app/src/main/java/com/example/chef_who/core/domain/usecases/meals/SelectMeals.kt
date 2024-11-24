package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.domain.models.Category
import kotlinx.coroutines.flow.Flow

class SelectMeals(
    private val mealsDao: MealsDao
) {
     operator fun invoke(): Flow<List<Category>> {
        return mealsDao.getMeals()
    }

}