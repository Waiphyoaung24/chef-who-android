package com.example.chef_who.core.data.network.dto

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import kotlinx.coroutines.flow.Flow

class MealsRepositoryImpl(
    private val mealsApi: ChefWhoApi
) : MealsRepository {



    override fun getFoodMenu(sources: List<String>): Flow<PagingData<FoodMenu>> {
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

    override fun searchCategory(keyword : String): Flow<PagingData<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMenuList(): List<Food> {
        return mealsApi.getMenuList()
    }


    override suspend fun getHomeType(): Dashboard {
       return mealsApi.getHomeType()

    }

    //retrieve category Ids from end point
    override suspend fun getAllCategoryIds(): List<Category> {
        return mealsApi.getAllCategoryIds()
    }

    override suspend fun getCartList(): List<Cart> {
        return mealsApi.getCartIdList()
    }


}
