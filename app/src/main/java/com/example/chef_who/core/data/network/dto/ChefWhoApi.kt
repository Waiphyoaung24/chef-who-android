package com.example.chef_who.core.data.network.dto

import android.graphics.pdf.PdfDocument.Page
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.util.Constants
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChefWhoApi {


    //Get Menu from our food endpoint
    @GET("our-foods")
    suspend fun getFoodMenu(
        @Query("_limit") page: Int
    ): List<FoodMenu>

    //Get Menu from our food endpoint
    @GET("view-type")
    suspend fun getHomeType(
    ): Dashboard

    @GET("onboarding")
    suspend fun getOnBoaridng(): List<Page>

    @GET("getAllCategoryIds")
    suspend fun getAllCategoryIds(): List<Category>

    @GET("getMenuList")
    suspend fun getMenuList(): List<Food>

    @GET("getUserCart")
    suspend fun getCartIdList(): List<Cart>

    @GET("login")
    suspend fun login():Boolean

    @POST("register")
    suspend fun registerUser(@Query("first_name") firstname: String,
                             @Query("last_name") lastname :String,
                             @Query("email") email : String,
                             @Query("password")password : String):ResponseObject


}