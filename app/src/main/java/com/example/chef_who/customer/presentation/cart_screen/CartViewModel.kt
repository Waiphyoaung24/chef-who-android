package com.example.chef_who.customer.presentation.cart_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val mAppEntryUseCases: AppEntryUseCases,
) : ViewModel() {
    private val _cartItems = mutableStateOf<List<Cart>>(emptyList())
    val cartItems: State<List<Cart>> = _cartItems

    //
//
//
//    // Add item to the cart
//    fun addToCart(item: Cart) {
//        _cartItems.value = _cartItems.value.toMutableList().apply {
//            val existingItem = find { it.id == item.id }
//            if (existingItem != null) {
//                remove(existingItem)
//                add(existingItem.copy(quantity = existingItem.quantity + item.quantity))
//            } else {
//                add(item)
//            }
//        }
//    }
//
//    // Remove item from the cart
//    fun removeFromCart(itemId: Int) {
//        _cartItems.value = _cartItems.value.toMutableList().apply {
//            removeIf { it.id == itemId }
//        }
//    }
//
//    // Clear the cart
//    fun clearCart() {
//        _cartItems.value = emptyList()
//    }
    init {
        // Load cart items from DataStore
        viewModelScope.launch {
            mAppEntryUseCases.getCartItems().collectLatest { items ->
                _cartItems.value = items
            }
        }
    }
    fun addToCart(item: Cart) {
        viewModelScope.launch {
            val currentCart = _cartItems.value.toMutableList()
            val existingItem = currentCart.find { it.id == item.id }
            if (existingItem != null) {
                currentCart.remove(existingItem)
                currentCart.add(existingItem.copy(quantity = existingItem.quantity + item.quantity))
            } else {
                currentCart.add(item)
            }
            mAppEntryUseCases.saveCartItem.invoke(currentCart)
            _cartItems.value = currentCart
        }
    }

}

