package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.util.Constants
import com.example.chef_who.customer.domain.Dashboard
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    //get meal list from api
    @GET("top-headlines")
    suspend fun getMeals(
        @Query("page") page: Int,
        @Query("q") sources: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): MealListResponse


    //search meal list from api
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): MealListResponse


    //Get Menu from our food endpoint
    @GET("our-foods")
    suspend fun getFoodMenu(
        @Query("_limit") page: Int
    ): List<FoodMenu>

    //Get Menu from our food endpoint
    @GET("viewType")
    suspend fun getHomeType(
    ): Dashboard


}