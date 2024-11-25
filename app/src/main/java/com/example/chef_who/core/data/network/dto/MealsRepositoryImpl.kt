package com.example.chef_who.core.data.network.dto

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import kotlinx.coroutines.flow.Flow

class MealsRepositoryImpl(
    private val mealsApi: ChefWhoApi
) : MealsRepository {





    override suspend fun search(keyword : String): List<Food> {
        return mealsApi.search(keyword)
    }

    override suspend fun getMenuList(catId :String,sellerId :String): List<Food> {
        return mealsApi.getMenuList(catId,sellerId)
    }

    override suspend fun createOrder(order: Order): ResponseObject {
        return mealsApi.createOrder(order)
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

    override suspend fun getSellerList(): List<Seller> {
        return mealsApi.getSellerList()
    }


}
