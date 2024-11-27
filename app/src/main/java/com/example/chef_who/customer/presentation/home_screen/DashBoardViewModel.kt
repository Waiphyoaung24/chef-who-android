package com.example.chef_who.customer.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    val mData: MutableState<List<Dashboard.Item>> = mutableStateOf(emptyList())
    val mCategoryIds: MutableState<List<Category>> = mutableStateOf(emptyList())
    val mSellerList: MutableState<List<Seller>> = mutableStateOf(emptyList())


    var menuList = mutableStateOf(mutableListOf<Food>())
        private set

    var isMenuLoaded = mutableStateOf(false)
        private set

    private val _isLoading = MutableStateFlow(true) // Initial loading state
    val isLoading: StateFlow<Boolean> get() = _isLoading


    var searchKeyword = mutableStateOf("")
        private set
    var catId = mutableStateOf("")
        private set
    var sellerId = mutableStateOf("")
        private set


    fun fetchMenuListByCategory(newString: String, type: String) {
        when (type) {
            "seller" -> sellerId.value = newString
            "category" -> catId.value = newString
            "search" -> searchKeyword.value = newString
        }
    }


    init {
        getCategoryIds()
        getHomeType()
        getSellerList()
    }

    private fun getSellerList() {
        viewModelScope.launch {
            try {
                mSellerList.value = mealsUseCases.getSellerList.invoke()
            } catch (e: Exception) {
                Log.d("error", e.message.toString())

            }
        }
    }

    private fun getCategoryIds() {
        viewModelScope.launch {
            try {
                mCategoryIds.value = mealsUseCases.getCategoryIds.invoke()
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }

    }

    fun getMenuList() {
        if (!isMenuLoaded.value) {
            viewModelScope.launch {
                try {
                    addMenuItem(mealsUseCases.getMenuList.invoke(sellerId.value, catId.value))
                    isMenuLoaded.value = true
                    sellerId.value = ""
                    catId.value = ""
                } catch (e: Exception) {
                    Log.d("error", e.message.toString())
                }
            }
        }
    }


    fun searchMenuList() {
        if (!isMenuLoaded.value) {
            viewModelScope.launch {
                try {
                    val searchResults = mealsUseCases.searchMeals.invoke(searchKeyword.value)
                    addMenuItem(searchResults)
                    isMenuLoaded.value = true
                    searchKeyword.value = ""


                } catch (e: Exception) {
                    Log.d("error", e.message.toString())
                }
            }
        }
    }

    fun resetMenuLoadedStatus() {
        isMenuLoaded.value = false
    }

    fun refreshData() {
        getSellerList()
    }

    private fun addMenuItem(item: List<Food>) {
        // Update the state to create a new reference, triggering recomposition
        menuList.value = item.toMutableList()
    }


    private fun getHomeType() {
        viewModelScope.launch {
            try {
                mData.value = mealsUseCases.mHomeType.invoke().data
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
            delay(500)
            _isLoading.value = false
        }


    }

}


