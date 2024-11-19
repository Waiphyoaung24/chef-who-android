package com.example.chef_who.core.data.network.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.chef_who.core.domain.models.Article

class MealsPagingSource(
    private val mealsApi: MealApi,
    private val sources: String,
) : PagingSource<Int, Article>() {

    private var totalMealsCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val mealsResponse = mealsApi.getMeals(sources = sources, page = page)
            totalMealsCount = mealsResponse.articles.size
            val articles = mealsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalMealsCount == mealsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e : Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}