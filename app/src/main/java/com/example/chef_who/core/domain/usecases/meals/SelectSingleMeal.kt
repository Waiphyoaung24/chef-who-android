package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

class SelectSingleMeal(
    private val mealsDao: MealsDao
) {
    suspend operator fun invoke(url: String): Article? {
        return mealsDao.getArticle(url)
    }

}