package com.example.chef_who.core.domain.manager

import android.content.Context
import com.example.chef_who.core.domain.models.Cart
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    suspend fun clearCartItems()
    fun readAppEntry() : Flow<Boolean>
    suspend fun saveCartItems(cartItems : List<Cart>)
    fun getCartItems(): Flow<List<Cart>>
    fun getContext (): Context
    suspend fun getAllData(): Map<String, Any?>
}