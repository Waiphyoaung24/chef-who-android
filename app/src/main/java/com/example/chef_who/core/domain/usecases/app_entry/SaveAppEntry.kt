package com.example.chef_who.core.domain.usecases.app_entry

import com.example.chef_who.core.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
         localUserManager.saveAppEntry()
    }
}