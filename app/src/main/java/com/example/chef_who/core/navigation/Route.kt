package com.example.chef_who.core.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object BookmarkScreen : Route(route = "bookMarkScreen")

    object DetailsScreen : Route(route = "detailsScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object ChefWhoNavigation : Route(route = "chefWhoNavigation")

    object ChefWhoNavigatorScreen : Route(route = "chefWhoNavigator")
}

