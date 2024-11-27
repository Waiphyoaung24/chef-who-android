package com.example.chef_who.activities

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chef_who.core.domain.models.MenuItem
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.models.SellerProfileResponse
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.core.domain.usecases.app_entry.AppEntryUseCases
import com.example.chef_who.core.domain.usecases.auth.AuthUseCases
import com.example.chef_who.core.domain.usecases.auth.SetUpSellerProfile
import com.example.chef_who.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {


    val user = mutableStateOf(User("", "", "", "", ""))
    private val _splashCondition = mutableStateOf(true)
    var splashCondition: State<Boolean> = _splashCondition
    private val _startDestination = mutableStateOf(Route.ChefWhoNavigatorScreen.route)
    val startDestination: State<String> = _startDestination
    val navigation = MutableSharedFlow<String>()
    val userRegistered = mutableStateOf<Boolean>(false)
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()
    val userPreferences = appEntryUseCases.userPreferences


    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                //1st case user first time, onboaring -> login,register
                //2nd case user already register -> home screen
                _startDestination.value = Route.ChefWhoNavigatorScreen.route
            } else {
                _startDestination.value = Route.OnBoardingScreen.route

            }

            delay(400)
            _splashCondition.value = false

        }.launchIn(viewModelScope)


    }


    fun triggerToast(message: String) {
        _toastMessage.value = message
    }

    fun clearToast() {
        _toastMessage.value = null
    }


    fun createUser() {
        viewModelScope.launch {
            try {
                val response = authUseCases.registerAuth.createUser(user.value)
                if (response.message == "success") {
                    if (response.user != null) {
                        user.value = user.value.copy(
                            id = response.user.id,
                            first_name = response.user.first_name,
                            last_name = response.user.last_name
                        )
                        appEntryUseCases.userPreferences.saveUser(
                            user.value.id,
                            user.value.first_name + user.value.last_name
                        )
                    }

                }
                userRegistered.value = true
                navigation.emit(Route.ChefWhoNavigatorScreen.route)
            } catch (e: Exception) {
                Log.d("Error", e.toString())

            }
        }


    }

    fun setUpSellerProfile() {
        val menuItems = listOf(
            MenuItem(
                name = "Spaghetti Carbonara",
                price = 120.5,
                image = "https://images.unsplash.com/photo-1562967916-eb82221dfb38?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDg2OTN8MHwxfGFsbHwxfHx8fHx8fHwxNjE2NjU4NDA1&ixlib=rb-1.2.1&q=80&w=400"
            ),
            MenuItem(
                name = "Grilled Salmon",
                price = 200.75,
                image = "https://images.unsplash.com/photo-1546069901-eacef0df6022?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwyMDg2OTN8MHwxfGFsbHwxfHx8fHx8fHwxNjE2NjU4NDA1&ixlib=rb-1.2.1&q=80&w=400"
            )
        )
        val seller = SellerProfileResponse(
            title = user.value.first_name + "'s Bistro",
            imageUrl = "https://images.unsplash.com/photo-1496379896897-7b57622f431b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2689&q=80",
            subTitle = "Fine Dining",
            menu_items = menuItems,
            user_id = user.value.id
        )
        viewModelScope.launch {
            try {
                val response = authUseCases.setupSellerProfile.invoke(seller)
                Log.d("response", response.message)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            try {
                val response = authUseCases.loginAuth.invoke(user.value)
                if (response.message == "success") {
                    if (response.user != null) {
                        user.value = user.value.copy(
                            id = response.user.id,
                            first_name = response.user.first_name,
                            last_name = response.user.last_name
                        )
                    }
                    appEntryUseCases.userPreferences.saveUser(
                        user.value.id,
                        user.value.first_name + user.value.last_name
                    )
                    userRegistered.value = true
                    navigation.emit(Route.ChefWhoNavigatorScreen.route)
                } else {
                    _toastMessage.value = "Invalid Credentials"
                }
            } catch (e: Exception) {
                Log.d("Error", e.toString())

            }
        }


    }

    fun onLogout() {
        viewModelScope.launch {
            try {
                appEntryUseCases.userPreferences.clearAllData()
            } catch (e: Exception) {
                Log.d("error", e.toString())

            }
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