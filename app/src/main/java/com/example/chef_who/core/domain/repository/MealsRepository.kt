package com.example.chef_who.core.domain.repository

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface MealsRepository {

    fun getMeals(sources: List<String>): Flow<PagingData<Article>>
    fun searchMeals(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

}