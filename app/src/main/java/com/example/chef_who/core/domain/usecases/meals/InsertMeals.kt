package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.data.local.MealsDao
import com.example.chef_who.core.domain.models.Article

class InsertMeals(
    private val mealsDao: MealsDao
) {
    suspend operator fun invoke(article: Article) {
        mealsDao.upsert(article)
    }

}