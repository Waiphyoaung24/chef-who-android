package com.example.chef_who.core.data.network.dto

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class

MealsRepositoryImpl(
    private val mealsApi: MealApi
) : MealsRepository {

    override fun getMeals(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MealsPagingSource(
                    mealsApi = mealsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchMeals(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchMealPagingSource(
                    api = mealsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

}