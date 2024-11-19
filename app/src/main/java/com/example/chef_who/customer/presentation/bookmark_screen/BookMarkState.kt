package com.example.chef_who.customer.presentation.bookmark_screen

import com.example.chef_who.core.domain.models.Article

data class BookMarkState(
    val articles: List<Article> = emptyList()
)
