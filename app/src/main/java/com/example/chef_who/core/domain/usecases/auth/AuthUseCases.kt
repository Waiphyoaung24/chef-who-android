package com.example.chef_who.core.domain.usecases.auth

import com.example.chef_who.core.domain.usecases.app_entry.ReadAppEntry
import com.example.chef_who.core.domain.usecases.app_entry.SaveAppEntry

data class AuthUseCases(
    val loginAuth: LoginAuth,
    val registerAuth : RegisterAuth,
    val getCurrentUser : GetCurrentUser
)
