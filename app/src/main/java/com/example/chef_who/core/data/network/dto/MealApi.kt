package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.util.Constants
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


}