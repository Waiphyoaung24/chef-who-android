package com.example.chef_who.core.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object MenuListScreen : Route(route = "menuListScreen")

    object AddListingScreen : Route(route = "addListingScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object UserDashBoardScreen : Route(route = "userDashBoardScreen")


    object ProfileScreen : Route(route = "profileScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")


    object ChefWhoNavigatorScreen : Route(route = "chefWhoNavigator")

    object LoginScreen : Route(route = "loginScreen")

    object RegisterScreen : Route(route = "registerScreen")


}

