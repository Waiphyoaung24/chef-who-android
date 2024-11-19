package com.example.chef_who.core.data.network.dto

import com.example.chef_who.core.domain.models.Article

data class MealListResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
