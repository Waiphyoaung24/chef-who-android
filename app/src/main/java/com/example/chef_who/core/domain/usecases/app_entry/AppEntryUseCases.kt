package com.example.chef_who.core.domain.usecases.app_entry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
    val saveCartItem : SaveCartItem,
    val getCartItems : GetCartItems
)
