package com.example.chef_who.customer.presentation.search_screen

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.chef_who.core.domain.models.Category
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery : String = "",
    val articles: Flow<PagingData<Category>>?= null
)
