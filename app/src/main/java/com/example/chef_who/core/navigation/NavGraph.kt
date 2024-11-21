package com.example.chef_who.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.chef_who.core.presentation.bottom_nav_bar.MealsNavigator
import com.example.chef_who.core.presentation.onboarding.OnBoardingScreen
import com.example.chef_who.core.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.ChefWhoNavigation.route,
            startDestination = Route.ChefWhoNavigatorScreen.route
        ) {
            composable(route = Route.ChefWhoNavigatorScreen.route) {
                MealsNavigator()
            }
        }
    }
}