package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.domain.models.Category

class InsertMeals(
    private val mealsDao: MealsDao
) {
    suspend operator fun invoke(category: Category) {
        mealsDao.upsert(category)
    }

}