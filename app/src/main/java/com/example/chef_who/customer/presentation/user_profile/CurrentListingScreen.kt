package com.example.chef_who.customer.presentation.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.chef_who.activities.MainViewModel
import com.example.chef_who.core.presentation.auth.MyTextField
import com.example.chef_who.core.presentation.common.FoodList
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.home_screen.DashBoardViewModel
import com.example.chef_who.customer.presentation.home_screen.MenuListScreen

@Composable
fun CurrentListingScreen(modifier: Modifier = Modifier, foodList: List<Food>) {

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Form to Add/Update Menu Item

        FoodList(modifier = Modifier, foodList, navigateToDetail = { })

    }
}