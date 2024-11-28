package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.repository.MealsRepository
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food

class MealsRepositoryImpl(
    private val mealsApi: ChefWhoApi
) : MealsRepository {


    override suspend fun search(keyword: String): List<Food> {
        return mealsApi.search(keyword)
    }

    override suspend fun getMenuList(catId: String, sellerId: String): List<Food> {
        return mealsApi.getMenuList(catId, sellerId)
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

    override suspend fun getOrderHistoryList(userId: String): List<OrderHistoryResponse> {
        return mealsApi.getOrderHistoryList(userId)
    }

    override suspend fun getActiveOrders(sellerId: String): List<OrderActiveResponse> {
        return mealsApi.getActiveOrders(sellerId)
    }

    override suspend fun updateOrderStatus(orderId: String, orderStatus: String): ResponseObject {
        return mealsApi.updateOrderStatus(orderId, orderStatus)
    }

    override suspend fun addMenuItemToCloud(sellerId: String, food: FoodMenu): ResponseObject {
        return mealsApi.addMenuItemToCloud(sellerId = sellerId, food)
    }


}
