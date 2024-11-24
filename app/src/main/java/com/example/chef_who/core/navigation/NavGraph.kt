package com.example.chef_who.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chef_who.activities.MainViewModel
import com.example.chef_who.core.presentation.auth.SignUpScreen
import com.example.chef_who.core.presentation.bottom_nav_bar.MealsNavigator
import com.example.chef_who.core.presentation.onboarding.OnBoardingScreen
import com.example.chef_who.core.presentation.onboarding.OnBoardingViewModel
import com.example.chef_who.customer.presentation.home_screen.DashBoardViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = startDestination) {


        composable(route = Route.OnBoardingScreen.route) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(onEvent = viewModel::onEvent)
        }


        composable(route = Route.ChefWhoNavigatorScreen.route) {
            MealsNavigator()
        }
    }
}




