package com.example.chef_who.customer.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import com.example.chef_who.customer.domain.Dashboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases
) : ViewModel() {
    val mData: MutableState<List<Dashboard.Item>> = mutableStateOf(emptyList())

    val meals = mealsUseCases.getMeals(
        sources = listOf("techcrunch")
    ).cachedIn(viewModelScope)


    fun getHomeType() {
        viewModelScope.launch {
            try {
                mData.value = mealsUseCases.mHomeType.invoke().data

            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

}


