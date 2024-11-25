package com.example.chef_who.activities

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.auth.AuthUseCases
import com.example.chef_who.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    val user = mutableStateOf(User(0, "", "", "", ""))
    private val _splashCondition = mutableStateOf(true)
    var splashCondition: State<Boolean> = _splashCondition
    private val _startDestination = mutableStateOf(Route.ChefWhoNavigatorScreen.route)
    val startDestination: State<String> = _startDestination

    val navigation = MutableSharedFlow<String>()


    val userRegistered = mutableStateOf<Boolean>(false)


    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                //1st case user first time, onboaring -> login,register
                //2nd case user already register -> home screen
                _startDestination.value = Route.ChefWhoNavigatorScreen.route
            } else {
                _startDestination.value = Route.OnBoardingScreen.route

            }

            delay(500)
            _splashCondition.value = false

        }.launchIn(viewModelScope)


    }


    fun createUser() {
        viewModelScope.launch {
            authUseCases.registerAuth.createUser(user.value)
            userRegistered.value = true
            navigation.emit(Route.ChefWhoNavigatorScreen.route)
        }


    }

    fun onTextChange(value: String, labelValue: String) {
        when (labelValue) {
            "First Name" -> user.value = user.value.copy(first_name = value)
            "Last Name" -> user.value = user.value.copy(last_name = value)
            "Email" -> user.value = user.value.copy(email = value)
            "Password" -> user.value = user.value.copy(password = value)

        }


    }
}