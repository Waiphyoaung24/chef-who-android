package com.example.chef_who.customer.presentation.user_dashboard

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerDashboardViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases,
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    val mHistoryList: MutableState<List<OrderHistoryResponse>> = mutableStateOf(emptyList())
    val mActiveList: MutableState<List<OrderActiveResponse>> = mutableStateOf(emptyList())
    val userPreferences = appEntryUseCases.userPreferences


    init {
        fetchAllOrderHistory()
        fetchAllActiveOrders()
    }


    private fun fetchAllOrderHistory() {
        val userId = userPreferences.userIdFlow
        viewModelScope.launch {

            try {
                mHistoryList.value =
                    mealsUseCases.getOrderHistory.invoke(userId.toString())
                Log.d("test", "user id is " + userId.toString())
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }

    }


    private fun fetchAllActiveOrders() {
        viewModelScope.launch {
            try {
                mActiveList.value = mealsUseCases.getActiveOrders.invoke("2")
            } catch (e: Exception) {
                Log.d("Error", e.toString())

            }
        }
    }

    fun updateOrderStatus(orderItemId: String, newStatus: String) {
        viewModelScope.launch {
            try {
                val response = mealsUseCases.updateOrderStatus.invoke(orderItemId, newStatus)
                if (response.message == "success") {
                    Log.d("data", "successfully updated to Accepted")
                } else {
                    Log.d("Failed", "failed")
                }
            } catch (e: Exception) {
                Log.d("message", e.toString())
            }
        }
    }

    fun refreshData() {
        fetchAllOrderHistory()
        fetchAllActiveOrders()
    }

}