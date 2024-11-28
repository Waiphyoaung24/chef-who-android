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
        val FIRST_NAME_KEY = stringPreferencesKey("first_name")
        val LAST_NAME_KEY = stringPreferencesKey("last_name")
        val IS_SELLER_KEY = booleanPreferencesKey("is_Seller")
    }

    // Save user data
    suspend fun saveUser(userId: String, firstName: String, lastName: String,isSeller : Boolean) : Boolean {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[FIRST_NAME_KEY] = firstName
            preferences[LAST_NAME_KEY] = lastName
            preferences[IS_SELLER_KEY] = isSeller
        }
        return true
    }
    suspend fun setSeller(){
        context.dataStore.edit { preferences ->
            preferences[IS_SELLER_KEY] = true
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
           preferences.clear()
        }
    }


    // Retrieve user data
    val firstNameFlow = context.dataStore.data.map { preferences ->
        preferences[FIRST_NAME_KEY]
    }
    val lastNameFlow = context.dataStore.data.map { preferences ->
        preferences[LAST_NAME_KEY]
    }
    val userIdFlow = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY]
    }
    val isSellerFlow = context.dataStore.data.map { preferences ->
        preferences[IS_SELLER_KEY] ?: false
    }


}