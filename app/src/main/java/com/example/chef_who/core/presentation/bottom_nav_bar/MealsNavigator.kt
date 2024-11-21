package com.example.chef_who.core.presentation.bottom_nav_bar

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.navigation.Route
import com.example.chef_who.customer.domain.listOfFood
import com.example.chef_who.customer.presentation.bookmark_screen.BookMarkViewModel
import com.example.chef_who.customer.presentation.bookmark_screen.BookmarkScreen
import com.example.chef_who.customer.presentation.detail_screen.DetailsScreen
import com.example.chef_who.customer.presentation.detail_screen.DetailsViewModel
import com.example.chef_who.customer.presentation.finalize_home.ShowDashboard
import com.example.chef_who.customer.presentation.home_screen.HomeViewModel
import com.example.chef_who.customer.presentation.home_screen.MenuListScreen
import com.example.chef_who.customer.presentation.search_screen.SearchScreen
import com.example.chef_who.customer.presentation.search_screen.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.MenuListScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                viewModel.getHomeType()
                ShowDashboard(
                    viewModel.mData.value, navigateToMenuList = { i ->
                        navigateToTab(
                            navController = navController,
                            route = Route.MenuListScreen.route
                        )
                    }
                )
            }
            composable(route = Route.MenuListScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()

                MenuListScreen(
                    title = "Delicious\nfood for you",
                    food = listOfFood,
                    navigateToDetail = { i ->
                        navigateToDetails(
                            navController = navController,
                            foodId = i
                        )
                        Log.d("test food is", i)
                    })
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { food ->
                        navigateToDetails(
                            navController = navController,
                            foodId = food
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<String?>("food")
                    ?.let {
                        DetailsScreen(data = listOfFood[2],
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { food ->
                        navigateToDetails(
                            navController = navController,
                            foodId = food
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, foodId: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("food", foodId)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

private fun navigateToMenuList(navController: NavController, sellerId: String) {
    navController.currentBackStackEntry?.savedStateHandle?.set("sellerId", sellerId)
    navController.navigate(
        route = Route.MenuListScreen.route
    )
}