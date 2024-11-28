package com.example.chef_who.core.presentation.bottom_nav_bar

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.chef_who.core.presentation.auth.SignInScreen
import com.example.chef_who.core.presentation.auth.SignUpScreen
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.cart_screen.CartViewModel
import com.example.chef_who.customer.presentation.cart_screen.CartScreen
import com.example.chef_who.customer.presentation.detail_screen.DetailsScreen
import com.example.chef_who.customer.presentation.home_screen.AuthRequiredScreen
import com.example.chef_who.customer.presentation.home_screen.ShowDashboard
import com.example.chef_who.customer.presentation.home_screen.DashBoardViewModel
import com.example.chef_who.customer.presentation.home_screen.MenuListScreen
import com.example.chef_who.customer.presentation.update.components.ShowLoading
import com.example.chef_who.customer.presentation.user_dashboard.CustomerDashboardViewModel
import com.example.chef_who.customer.presentation.user_dashboard.UserDashBoardScreen
import com.example.chef_who.customer.presentation.user_profile.AddListing
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
                val viewModelMain: MainViewModel = hiltViewModel()
                val isLoading by viewModel.isLoading
                viewModel.refreshData()
                if (isLoading) {
                    ShowLoading()
                } else {

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
                            viewModel.fetchMenuListByCategory(
                                viewModel.searchKeyword.value,
                                "search"
                            )
                            navigateToMenuList(navController)
                        },
                        onCatItemClick = {
                            navigateToTab(
                                navController = navController,
                                route = Route.MenuListScreen.route,
                            )
                            viewModel.fetchMenuListByCategory(it.toString(), "category")
                        },
                        navigateToCart = {
                            navigateToTab(
                                navController,
                                Route.BookmarkScreen.route
                            )
                        }
                    )
                }
            }

            //Menu List Screen Compose

            composable(route = Route.MenuListScreen.route) { backStackEntry ->
                val viewModel: DashBoardViewModel =
                    hiltViewModel(navController.getBackStackEntry(Route.HomeScreen.route))

                LaunchedEffect(
                    key1 = viewModel.sellerId.value,
                    key2 = viewModel.searchKeyword.value,
                    key3 = viewModel.catId.value
                ) {
                    if (viewModel.sellerId.value.isNotEmpty()) {
                        viewModel.resetMenuLoadedStatus()
                        viewModel.getMenuList()
                    } else if (viewModel.catId.value.isNotEmpty()) {
                        viewModel.resetMenuLoadedStatus()
                        viewModel.getMenuList()
                    } else if (viewModel.searchKeyword.value.isNotEmpty()) {
                        viewModel.resetMenuLoadedStatus()
                        viewModel.searchMenuList()

                    }
                }
                if (!viewModel.isMenuLoaded.value) {
                    ShowLoading()
                } else {
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
            }


            //Food Detail Screen Compose
            composable(route = Route.DetailsScreen.route) {
                val viewModelCart: CartViewModel = hiltViewModel()
                val viewModelMain: MainViewModel = hiltViewModel()
                val userId = viewModelMain.user1.value.id
                var isRegistered = false


                if (!userId.equals(null)) {
                    isRegistered = true
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Food?>("food")
                    ?.let {

                        val data =
                            Cart(it.id, it.image, "in process", it.name, it.price.toDouble(), 1)
                        DetailsScreen(
                            isRegistered = isRegistered,
                            data = it,
                            navigateUp = { navController.navigateUp() },
                            addToCart = {
                                viewModelCart.addToCart(data)
                                navController.navigateUp()
                            },

                            )
                    }

            }

            //Registration Screen Compose

            composable(route = Route.RegisterScreen.route) {
                val viewModel: MainViewModel = hiltViewModel()
                val userId = viewModel.user1.value.id

                // case 1 -> user is registered just now and redirect to profile screen
                if (viewModel.userRegistered.value) {
                    navigateToTab(navController, Route.ProfileScreen.route)

                }
                // case 2 -> user is logout and first time login in
                else if (userId.isEmpty()) {
                    SignUpScreen(
                        onRegister = viewModel::createUser,
                        onTextChange = viewModel::onTextChange,
                        user = viewModel.user1.value,
                        navigateToLoginScreen = {
                            navigateToTab(
                                navController,
                                Route.LoginScreen.route
                            )
                        },
                        navigateToHomeScreen = {
                            navigateToTab(
                                navController,
                                Route.HomeScreen.route
                            )
                        }
                    )
                }
                //case 3 -> user is already login or registered state
                else {
                    UserProfile(
                        onBackBtnClick = {},
                        onClickSeller = {
                            navigateToTab(navController, Route.AddListingScreen.route)
                        },
                        onLogout = {
                            navigateToTab(navController, Route.HomeScreen.route)
                            viewModel.onLogout()
                        },
                        userName = viewModel.user1.value.first_name + viewModel.user1.value.last_name
                    )
                }

            }
            //User Dashboard Screen Compose
            composable(route = Route.ProfileScreen.route) {
                val viewModel: MainViewModel = hiltViewModel()

                UserProfile(
                    onBackBtnClick = {},
                    onClickSeller = {
                        navigateToTab(navController, Route.AddListingScreen.route)
                    },
                    onLogout = {
                        viewModel.onLogout()
                        navigateToTab(navController, Route.HomeScreen.route)
                    }, userName = viewModel.user1.value.first_name + viewModel.user1.value.last_name
                )
            }

            //Add Listing Screen Compose
            composable(route = Route.AddListingScreen.route) {
                val viewModel: DashBoardViewModel = hiltViewModel()
                val viewModelMain: MainViewModel = hiltViewModel()
                val context = LocalContext.current
                val toastMessage by viewModel.toastMessage
                val isLoading by viewModel.isLoading
                val userName = viewModelMain.user1.value.first_name


                // Observe toastMessage and show a Toast
                LaunchedEffect(toastMessage) {
                    toastMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        viewModel.resetToastMessage() // Reset after showing
                    }
                }
                LaunchedEffect(userName) {
                    if (!viewModelMain.user1.value.isSeller) {
                        viewModelMain.setUpSellerProfile(userName)
                    }
                }

                viewModel.fetchMenuListByCategory(viewModelMain.user1.value.id, "seller")
                viewModel.getMenuList()
                viewModel.resetMenuLoadedStatus()



                if (isLoading) {
                    ShowLoading()
                } else {
                    AddListing(
                        modifier = Modifier,
                        viewModel.menuList.value,
                        onTextChange = viewModel::onTextChange,
                        viewModel.food.value,
                        onClick = { viewModel.addMenuItemToCloud() }
                    )
                }
            }

            composable(route = Route.LoginScreen.route) {
                val viewModel: MainViewModel = hiltViewModel()
                val context = LocalContext.current
                val toastMessage by viewModel.toastMessage.collectAsState()

                LaunchedEffect(toastMessage) {
                    toastMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        viewModel.clearToast() // Clear the toast message after showing
                    }
                }
                if (!viewModel.userRegistered.value) {
                    SignInScreen(
                        modifier = Modifier,
                        onTextChange = viewModel::onTextChange,
                        viewModel.user1.value,
                        onLogin = viewModel::loginUser,
                        navigateUp = { navController.navigateUp() }
                    )
                } else {
                    UserProfile(
                        onBackBtnClick = {},
                        onClickSeller = {
                            navigateToTab(navController, Route.AddListingScreen.route)
                        },
                        onLogout = {
                            navigateToTab(navController, Route.HomeScreen.route)
                            viewModel.onLogout()
                        },
                        userName = viewModel.user1.value.first_name + viewModel.user1.value.last_name
                    )
                }
            }

            //User Dashboard Screen Compose
            composable(route = Route.UserDashBoardScreen.route) {
                val viewModel: CustomerDashboardViewModel = hiltViewModel()
                val viewModelMain: MainViewModel = hiltViewModel()

                val dataHistory = viewModel.mHistoryList
                val dataActive = viewModel.mActiveList
                val userId = viewModelMain.user1.value.id

                if (userId.isNotEmpty()) {
                    viewModel.refreshData(userId.toString())
                    UserDashBoardScreen(
                        dataHistory.value,
                        dataActive.value,
                        onSellerClick = { orderStatus, orderId ->
                            viewModel.updateOrderStatus(orderId, orderStatus)
                        })


                } else {
                    AuthRequiredScreen(onRegisterClick = {
                        navigateToTab(
                            navController,
                            Route.RegisterScreen.route
                        )
                    }, onSignInClick = {
                        navigateToTab(
                            navController,
                            Route.LoginScreen.route
                        )
                    })
                }
            }

            //Cart (Before checkout) Screen Compose
            composable(route = Route.BookmarkScreen.route) {
                val viewModelMain: MainViewModel = hiltViewModel()
                val viewModel: CartViewModel = hiltViewModel()
                val userId = viewModelMain.user1.value.id

                val toastMessage by viewModel.toastMessage
                val context = LocalContext.current
                // Observe toastMessage and show a Toast
                LaunchedEffect(toastMessage) {
                    toastMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        viewModel.resetToastMessage() // Reset after showing
                    }
                }

                CartScreen(
                    carts = viewModel.cartItems.value,
                    navigateToDetails = {},
                    removeCart = { itemId -> viewModel.removeFromCart(itemId) },
                    navigateUp = { navController.navigateUp() },
                    createOrder = {
                        viewModel.createOrder(userId.toString())
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

