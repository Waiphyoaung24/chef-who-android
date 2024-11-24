package com.example.chef_who.customer.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    val mData: MutableState<List<Dashboard.Item>> = mutableStateOf(emptyList())
    val mCategoryIds: MutableState<List<Category>> = mutableStateOf(emptyList())
    val mMenuList: MutableState<List<Food>> = mutableStateOf(emptyList())
    val isDataLoaded = mutableStateOf(false)

    val meals = mealsUseCases.getMeals(
        sources = listOf("techcrunch")
    ).cachedIn(viewModelScope)

    init {
        getCategoryIds()
        getHomeType()
    }


    private fun getCategoryIds() {
        viewModelScope.launch {
            try {
                mCategoryIds.value = mealsUseCases.getCategoryIds.invoke()
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
        isDataLoaded.value = true

    }

    fun getMenuList() {
        viewModelScope.launch {
            try {
                mMenuList.value = mealsUseCases.getMenuList.invoke()

            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    private fun getHomeType() {
        viewModelScope.launch {
            try {
                mData.value = mealsUseCases.mHomeType.invoke().data

            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

}


