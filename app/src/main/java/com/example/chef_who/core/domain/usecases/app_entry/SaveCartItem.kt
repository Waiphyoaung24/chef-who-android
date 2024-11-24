package com.example.chef_who.core.domain.usecases.app_entry

import com.example.chef_who.core.domain.manager.LocalUserManager
import com.example.chef_who.core.domain.models.Cart
import kotlinx.coroutines.flow.Flow

class SaveCartItem(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(carts : List<Cart>) {
        return localUserManager.saveCartItems(carts)
    }
}