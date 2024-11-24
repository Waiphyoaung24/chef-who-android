package com.example.chef_who.core.presentation.bottom_nav_bar

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chef_who.R
import com.example.chef_who.activities.MainViewModel
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.navigation.Route
import com.example.chef_who.core.presentation.auth.SignUpScreen
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.cart_screen.CartViewModel
import com.example.chef_who.customer.presentation.cart_screen.CartScreen
import com.example.chef_who.customer.presentation.detail_screen.DetailsScreen
import com.example.chef_who.customer.presentation.home_screen.ShowDashboard
import com.example.chef_who.customer.presentation.home_screen.DashBoardViewModel
import com.example.chef_who.customer.presentation.home_screen.MenuListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.baseline_attribution_24, text = "Profile"),
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
        Route.RegisterScreen.route -> 2
        else -> 0
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {

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
                        route = Route.RegisterScreen.route
                    )
                }
            }
        )

    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: DashBoardViewModel = hiltViewModel()
                val viewModelCart: CartViewModel = hiltViewModel()
                ShowDashboard(
                    viewModel.mData.value,
                    viewModel.mCategoryIds.value,
                    viewModelCart.cartItems.value.size,
                    navigateToMenuList = { i ->
                        navigateToTab(
                            navController = navController,
                            route = Route.MenuListScreen.route
                        )
                    },
                    navigateToCart = { navigateToTab(navController, Route.BookmarkScreen.route) }
                )
            }
            composable(route = Route.MenuListScreen.route) { backStackEntry ->
                val viewModel: DashBoardViewModel = hiltViewModel()
                viewModel.getMenuList()
                MenuListScreen(
                    title = "Delicious\nfood for you",
                    food = viewModel.mMenuList.value,
                    navigateToDetail = { i ->
                        navigateToDetails(
                            navController = navController,
                            food = i
                        )
                    })
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModelCart: CartViewModel = hiltViewModel()
                val context = LocalContext.current
                navController.previousBackStackEntry?.savedStateHandle?.get<Food?>("food")
                    ?.let {

                        val data =
                            Cart(it.id, it.image, "in process", it.name, it.price.toDouble(), 1)
                        DetailsScreen(data = it,
                            navigateUp = { navController.navigateUp() },
                            addToCart = { viewModelCart.addToCart(data) }

                        )
                    }

            }
            composable(route = Route.RegisterScreen.route) {
                val viewModel: MainViewModel = hiltViewModel()


                SignUpScreen(
                    onRegister = viewModel::createUser,
                    onTextChange = viewModel::onTextChange,
                    user = viewModel.user.value,
                    navigateToHomeScreen = { navigateToTab(navController, Route.HomeScreen.route) }
                )
                if (viewModel.userRegistered.value) {
                    navigateToTab(navController, Route.HomeScreen.route)
                }

            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: CartViewModel = hiltViewModel()
                CartScreen(
                    carts = viewModel.cartItems.value,
                    navigateToDetails = {},
                    navigateUp = { navController.navigateUp() })
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

private fun navigateToDetails(navController: NavController, food: Food) {
    navController.currentBackStackEntry?.savedStateHandle?.set("food", food)
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

