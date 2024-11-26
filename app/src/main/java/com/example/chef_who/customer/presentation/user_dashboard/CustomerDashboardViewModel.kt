package com.example.chef_who.customer.presentation.user_dashboard

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerDashboardViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases,
) : ViewModel() {

    val mHistoryList: MutableState<List<OrderHistoryResponse>> = mutableStateOf(emptyList())


    init {
        fetchAllOrderHistory()
        fetchAllActiveOrders()
    }

    private fun fetchAllOrderHistory() {
        viewModelScope.launch {

            try {
                mHistoryList.value = mealsUseCases.getOrderHistory.invoke("1")
                Log.d("test", "user id is " + mHistoryList.value.get(0).item_price)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }

    }

    fun fetchAllActiveOrders() {

    }

    fun refreshData() {
        fetchAllOrderHistory()
    }

}