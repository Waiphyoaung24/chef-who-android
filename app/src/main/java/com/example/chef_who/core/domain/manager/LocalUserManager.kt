package com.example.chef_who.core.domain.manager

import com.example.chef_who.core.domain.models.Cart
import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()
    fun readAppEntry() : Flow<Boolean>
    suspend fun saveCartItems(cartItems : List<Cart>)
    fun getCartItems(): Flow<List<Cart>>
}