package com.example.chef_who.core.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.chef_who.core.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Extension to create a DataStore instance
val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences @Inject constructor(localUserManager: LocalUserManager) {
    val context = localUserManager.getContext()

    // Keys for the data
    companion object {
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val IS_SELLER_KEY = booleanPreferencesKey("is_Seller")
    }

    // Save user data
    suspend fun saveUser(userId: String, userName: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = userName
            preferences[IS_SELLER_KEY] = false
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear() // Clear all keys in the DataStore
        }
    }

    // Retrieve user data
    val userNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY]
    }

    val userIdFlow = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }

    val isSellerFlow = context.dataStore.data.map { preferences ->
        preferences[IS_SELLER_KEY] ?: false
    }


}