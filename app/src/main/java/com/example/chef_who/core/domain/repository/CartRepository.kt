package com.example.chef_who.core.domain.repository

import com.example.chef_who.core.domain.models.Cart

interface CartRepository {
    suspend fun saveCartItems(cartItems : List<Cart>){}
    suspend fun getCartItems(cartItems : List<Cart>){}

}