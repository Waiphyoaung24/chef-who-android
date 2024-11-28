package com.example.chef_who.core.domain.repository

import com.example.chef_who.core.data.network.dto.ResponseObject
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.usecases.meals.UpdateOrderStatus
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food

interface MealsRepository {

    suspend fun search(keyword: String): List<Food>
    suspend fun getMenuList(sellerId :String,catId :String): List<Food>
    suspend fun createOrder(order :Order) : ResponseObject
    suspend fun getHomeType(): Dashboard
    suspend fun getAllCategoryIds():List<Category>
    suspend fun getCartList():List<Cart>
    suspend fun getSellerList():List<Seller>
    suspend fun getOrderHistoryList(userId : String):List<OrderHistoryResponse>
    suspend fun getActiveOrders(sellerId: String):List<OrderActiveResponse>
    suspend fun updateOrderStatus(orderId : String,orderStatus: String) : ResponseObject
    suspend fun addMenuItemToCloud(sellerId: String,food: FoodMenu) : ResponseObject



}