package com.example.chef_who.core.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.chef_who.core.data.manager.PreferenceKeys.APP_ENTRY
import com.example.chef_who.core.data.manager.PreferenceKeys.CART_ITEMS_KEY
import com.example.chef_who.core.domain.manager.LocalUserManager
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.util.Constants
import com.example.chef_who.core.util.dataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {
    private val gson = Gson()


    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override suspend fun clearCartItems() {
        context.dataStore.edit { preferences ->
            preferences.remove(CART_ITEMS_KEY) // Remove the cart items key
        }
    }

    override suspend fun getAllData(): Map<String, Any?> {
        val allData = mutableMapOf<String, Any?>()
        context.dataStore.data.collect { preferences ->
            preferences.asMap().forEach { (key, value) ->
                allData[key.name] = value
            }
        }
        return allData
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }

    override suspend fun saveCartItems(cartItems: List<Cart>) {
        val cartJson = gson.toJson(cartItems)
        context.dataStore.edit { preferences ->
            preferences[CART_ITEMS_KEY] = cartJson
        }
    }

    override fun getCartItems(): Flow<List<Cart>> {
        return context.dataStore.data.map { preferences ->
            val cartJson = preferences[CART_ITEMS_KEY] ?: "[]"
            gson.fromJson(cartJson, object : TypeToken<List<Cart>>() {}.type)
        }
    }

    override fun getContext(): Context {
        return context
    }

}

private val readOnlyProperty = preferencesDataStore(name = Constants.USER_SETTINGS)

val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys {
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    val CART_ITEMS_KEY = stringPreferencesKey(Constants.CART_ITEMS)

}