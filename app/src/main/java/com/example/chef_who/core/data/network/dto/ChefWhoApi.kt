package com.example.chef_who.core.data.network.dto

import android.graphics.pdf.PdfDocument.Page
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.models.SellerProfileResponse
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChefWhoApi {


    //Get Menu from our food endpoint
    @GET("view-type")
    suspend fun getHomeType(
    ): Dashboard

    @GET("onboarding")
    suspend fun getOnBoaridng(): List<Page>

    @GET("getAllCategoryIds")
    suspend fun getAllCategoryIds(): List<Category>

    @GET("getMenuList")
    suspend fun getMenuList(
        @Query("seller_id") sellerId: String?,
        @Query("category_id") catId: String?,
    ): List<Food>

    @GET("getUserCart")
    suspend fun getCartIdList(): List<Cart>


    @POST("register")
    suspend fun registerUser(
        @Query("first_name") firstname: String,
        @Query("last_name") lastname: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): ResponseObject

    @POST("login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): ResponseObject

    @POST("createOrder")
    suspend fun createOrder(@Body order: Order): ResponseObject

    @POST("create-seller")
    suspend fun setupSellerProfile(@Body sellerProfile: SellerProfileResponse): ResponseObject

    @GET("search")
    suspend fun search(@Query("keyword") keyword: String): List<Food>

    @GET("getSellersList")
    suspend fun getSellerList(): List<Seller>

    @GET("getOrderHistory")
    suspend fun getOrderHistoryList(@Query("user_id") userId: String): List<OrderHistoryResponse>

    @GET("getActiveOrder")
    suspend fun getActiveOrders(@Query("seller_id") sellerId: String): List<OrderActiveResponse>

    @POST("updateOrderStatus")
    suspend fun updateOrderStatus(
        @Query("order_item_id") orderId: String,
        @Query("order_status") orderStatus: String
    ): ResponseObject

}