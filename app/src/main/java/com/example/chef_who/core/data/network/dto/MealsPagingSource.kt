package com.example.chef_who.core.data.network.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chef_who.core.domain.models.FoodMenu

class MealsPagingSource(
    private val mealsApi: ChefWhoApi,
    private val sources: String,
) : PagingSource<Int, FoodMenu>() {

    private var totalMealsCount = 0
    override fun getRefreshKey(state: PagingState<Int, FoodMenu>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FoodMenu> {
        val page = params.key ?: 1
        return try {
            val mealsResponse = mealsApi.getFoodMenu(page = 20)
            LoadResult.Page(
                data = mealsResponse,
                nextKey = page + 10,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}