package com.example.chef_who.core.presentation.bottom_nav_bar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.chef_who.customer.presentation.user_dashboard.CustomerDashboardViewModel
import com.example.chef_who.customer.presentation.user_dashboard.UserDashBoardScreen
import com.example.chef_who.customer.presentation.user_profile.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsNavigator() {


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    val isBottomBarVisible = remember(key1 = backStackState) {
        navController.currentDestination?.route == Route.MenuListScreen.route
    }
//    val isFABVisible = remember(key1 = backStackState) {
//        navController.currentDestination?.route == Route.UserDashBoardScreen.route
//    }

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.baseline_article_24, text = "Dashboard"),
            BottomNavigationItem(icon = R.drawable.baseline_attribution_24, text = "Profile"),
        )
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.UserDashBoardScreen.route -> 1
        Route.RegisterScreen.route -> 2
        else -> 0
    }



    Scaffold(modifier = Modifier.fillMaxSize(),

//        floatingActionButton = {
//
//            if (isFABVisible) {
//                val context = LocalContext.current
//                val itemList = listOf(
//                    FABItem(icon = Icons.Rounded.Create, text = "Create"),
//                    FABItem(icon = Icons.Rounded.Edit, text = "Edit"),
//                )
//
//                CustomExpandableFAB(
//                    items = itemList,
//                    onItemClick = { item ->
//
//                        when (item.text) {
//                            "Create" -> Toast.makeText(
//                                context,
//                                "create clicked",
//                                Toast.LENGTH_SHORT
//                            ).show()
//
//                            "Edit" -> Toast.makeText(
//                                context,
//                                "Edit clicked",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show()
//                        }
//
//                    }
//                )
//            }
//        },
        bottomBar = {


            if (!isBottomBarVisible) {
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
                                route = Route.UserDashBoardScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.RegisterScreen.route
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

            //Main Home Screen Compose
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: DashBoardViewModel = hiltViewModel()
                val viewModelCart: CartViewModel = hiltViewModel()
                ShowDashboard(
                    viewModel.mData.value,
                    viewModel.mSellerList.value,
                    viewModel.mCategoryIds.value,
                    viewModelCart.cartItems.value.size,
                    navigateToMenuList = { it ->
                        navigateToTab(
                            navController = navController,
                            route = Route.MenuListScreen.route,
                        )
                        viewModel.fetchMenuListByCategory(it, "seller")
                    },
                    keyword = viewModel.searchKeyword.value,
                    onValueChanged = { newValue -> viewModel.searchKeyword.value = newValue },
                    onSearch = {
                        viewModel.fetchMenuListByCategory(viewModel.searchKeyword.value, "search")
                        navigateToMenuList(navController)
                    },
                    navigateToCart = { navigateToTab(navController, Route.BookmarkScreen.route) }
                )
            }

            //Menu List Screen Compose

            composable(route = Route.MenuListScreen.route) { backStackEntry ->
                val viewModel: DashBoardViewModel =
                    hiltViewModel(navController.getBackStackEntry(Route.HomeScreen.route))

                LaunchedEffect(
                    key1 = viewModel.sellerId.value,
                    key2 = viewModel.searchKeyword.value
                ) {
                    if (viewModel.sellerId.value.isNotEmpty()) {
                        viewModel.resetMenuLoadedStatus()
                        viewModel.getMenuList()
                    } else if (viewModel.searchKeyword.value.isNotEmpty()) {
                        viewModel.resetMenuLoadedStatus()
                        viewModel.searchMenuList()

                    }
                }
                MenuListScreen(
                    title = "Delicious\nfood for you",
                    navigateUp = { navController.navigateUp() },
                    food = viewModel.menuList.value,
                    navigateToDetail = { i ->
                        navigateToDetails(
                            navController = navController,
                            food = i
                        )
                    })
            }


            //Food Detail Screen Compose
            composable(route = Route.DetailsScreen.route) {
                val viewModelCart: CartViewModel = hiltViewModel()
                val context = LocalContext.current
                navController.previousBackStackEntry?.savedStateHandle?.get<Food?>("food")
                    ?.let {

                        val data =
                            Cart(it.id, it.image, "in process", it.name, it.price.toDouble(), 1)
                        DetailsScreen(
                            data = it,
                            navigateUp = { navController.navigateUp() },
                            addToCart = { viewModelCart.addToCart(data) },

                            )
                    }

            }

            //Registration Screen Compose

            composable(route = Route.RegisterScreen.route) {
                val viewModel: MainViewModel = hiltViewModel()

                if (!viewModel.userRegistered.value) {
                    navigateToTab(navController, Route.ProfileScreen.route)
                } else {
                    SignUpScreen(
                        onRegister = viewModel::createUser,
                        onTextChange = viewModel::onTextChange,
                        user = viewModel.user.value,
                        navigateToHomeScreen = {
                            navigateToTab(
                                navController,
                                Route.HomeScreen.route
                            )
                        }
                    )
                }

            }
            //User Dashboard Screen Compose
            composable(route = Route.ProfileScreen.route) {
                UserProfile(onBackBtnClick = {})
            }

            //User Dashboard Screen Compose
            composable(route = Route.UserDashBoardScreen.route) {
                val viewModel: CustomerDashboardViewModel = hiltViewModel()
                val data = viewModel.mHistoryList
                viewModel.refreshData()
                UserDashBoardScreen(data.value)
            }

            //Cart (Before checkout) Screen Compose
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: CartViewModel = hiltViewModel()
                CartScreen(
                    carts = viewModel.cartItems.value,
                    navigateToDetails = {},
                    navigateUp = { navController.navigateUp() },
                    createOrder = { viewModel.createOrder() }
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

private fun navigateToDetails(navController: NavController, food: Food) {
    navController.currentBackStackEntry?.savedStateHandle?.set("food", food)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

private fun navigateToMenuList(navController: NavController) {

    navController.navigate(
        route = Route.MenuListScreen.route
    )
}

