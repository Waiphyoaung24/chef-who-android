package com.example.chef_who.core.domain.repository

import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.*

interface MealsRepository {

    fun getFoodMenu(sources: List<String>): Flow<PagingData<FoodMenu>>
    fun searchCategory(keyword: String): Flow<PagingData<Category>>
    suspend fun getMenuList(): List<Food>

    suspend fun getHomeType(): Dashboard
    suspend fun getAllCategoryIds():List<Category>
    suspend fun getCartList():List<Cart>

}