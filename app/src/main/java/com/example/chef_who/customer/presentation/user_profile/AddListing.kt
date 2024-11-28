package com.example.chef_who.customer.presentation.user_profile

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.user_dashboard.SelectionRow
import com.example.chef_who.customer.presentation.user_dashboard.SpacerWidth

@Composable
fun AddListing(
    modifier: Modifier = Modifier,
    foodList: List<Food>,
    onTextChange: (String, String) -> Unit,
    food: FoodMenu,
    onClick:()->Unit
) {
    var selectedScreen by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        DashBoardSellerSelection(
            selectedTab = ({ selectedScreen = it }),
            modifier = Modifier,
            headers = listOf("Add Listing", "Current Listings")
        )

        when (selectedScreen) {
            0 -> ListingForm(modifier = Modifier, onTextChange = onTextChange, food = food, onClick = onClick)
            1 -> CurrentListingScreen(
                foodList = foodList,
                modifier = Modifier,
            )
        }
    }
}

@Composable
fun DashBoardSellerSelection(
    modifier: Modifier = Modifier,
    selectedTab: (Int) -> Unit,
    headers: List<String>
) {
    var selected by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        SpacerWidth(20.dp)
        headers.forEachIndexed { index, s ->
            SelectionRow(
                onClick = selectedTab,
                selected = index == selected,
                index = index,
                title = s,
                onValueChange = {
                    selected = it
                })
        }
    }
}