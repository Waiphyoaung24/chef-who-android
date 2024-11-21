package com.example.chef_who.core.domain.repository

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.customer.domain.Dashboard
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.*

interface MealsRepository {

    // fun getMeals(sources: List<String>): Flow<PagingData<Article>>
    fun searchMeals(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    fun getFoodMenu(sources: List<String>): Flow<PagingData<FoodMenu>>
    suspend fun getHomeType(): Dashboard
}