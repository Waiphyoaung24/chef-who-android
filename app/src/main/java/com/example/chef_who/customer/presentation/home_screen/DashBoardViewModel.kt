package com.example.chef_who.customer.presentation.home_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.models.User
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
    val food = mutableStateOf(FoodMenu(0, "", 0, "", "", ""))


    var menuList = mutableStateOf(mutableListOf<Food>())
        private set

    var isMenuLoaded = mutableStateOf(false)
        private set

    val isLoading = mutableStateOf(false) // Track loading state


    var searchKeyword = mutableStateOf("")
        private set
    var catId = mutableStateOf("")
        private set
    var sellerId = mutableStateOf("")
        private set

    var toastMessage = mutableStateOf<String?>(null)


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

    fun resetToastMessage() {
        toastMessage.value = null // Reset the Toast message after showing it
    }

    private fun addMenuItem(item: List<Food>) {
        // Update the state to create a new reference, triggering recomposition
        menuList.value = item.toMutableList()
    }

    fun addMenuItemToCloud() {
        viewModelScope.launch {
            isLoading.value = true // Start loading

            try {
                val response = mealsUseCases.addMenuItem.invoke(sellerId.value, food.value)
                if (response.message == "success") {
                    toastMessage.value = "Menu item added successfully!"
                    Log.d("data update", "Success")
                } else {
                    toastMessage.value = "Failed to add menu item!"
                    Log.d("data update", "Failed")
                }
            } catch (e: Exception) {
                toastMessage.value = "An error occurred: ${e.message}"
                Log.d("error", e.toString())
            } finally {
                isLoading.value = false // Stop loading
            }
        }
    }


    private fun getHomeType() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                mData.value = mealsUseCases.mHomeType.invoke().data
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            } finally {
                isLoading.value = false

            }
        }


    }

    fun onTextChange(value: String, labelValue: String) {
        when (labelValue) {
            "Menu Name" -> food.value = food.value.copy(name = value)
            "Image" -> food.value = food.value.copy(image = value)
            "Price" -> food.value = food.value.copy(price = value.toInt())

        }


    }

}


