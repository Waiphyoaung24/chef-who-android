package com.example.chef_who.core.domain.usecases.app_entry

import com.example.chef_who.core.util.UserPreferences

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
    val saveCartItem : SaveCartItem,
    val getCartItems : GetCartItems,
    val clearCartItems : ClearCartItems,
    val userPreferences: UserPreferences
)
