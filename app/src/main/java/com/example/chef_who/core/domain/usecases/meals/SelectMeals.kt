package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

class SelectMeals(
    private val mealsDao: MealsDao
) {
     operator fun invoke(): Flow<List<Article>> {
        return mealsDao.getMeals()
    }

}