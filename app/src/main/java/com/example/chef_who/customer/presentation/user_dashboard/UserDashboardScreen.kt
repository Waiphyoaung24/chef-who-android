package com.example.chef_who.customer.presentation.user_dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.chef_who.core.domain.models.Order
import com.example.chef_who.core.domain.models.OrderHistoryResponse
import com.example.chef_who.ui.theme.Black

val NFTGradient1 = Color(0XFF330a5a)
val NFTGradient2 = Color(0XFF080f22)
val NFTGradient3 = Color(0XFF091323)
val NFTBlueColor = Color(0XFF121F33)
val NFTGrayColor = Color(0XFFCFD8E6)
val NFTDarkBlueColor = Color(0XFF384A66)
val NFTBorderColor = Color(0XFFCFD8E6)
val NFTDarkPinkColor = Color(0XFF8900ff)
val NFTLightPinkColor = Color(0XFFb800ff)
val NFTSectionColor = Brush.verticalGradient(listOf(NFTDarkPinkColor, NFTLightPinkColor))
val NFTBackgroundColor = Brush.verticalGradient(
    listOf(NFTGradient1, NFTGradient2, NFTGradient3)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserDashBoardScreen(orderHistory: List<OrderHistoryResponse>) {

    val itemsList = listOf("Item 1", "Item 2")
    var selectedScreen by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        DashBoardSelection(selectedTab = ({ it -> selectedScreen = it }))
        Spacer(Modifier.height(24.dp))

        when (selectedScreen) {
            0 -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orderHistory.size) { item ->
                    ListItemForDashBoard(isOrderHistory = true, modifier = Modifier,orderHistory.get(item))
                }
            }

            1 -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orderHistory.size) { item ->
                    ListItemForDashBoard(isOrderHistory = false, modifier = Modifier,orderHistory.get(item))
                }
            }
        }


    }
}


@Composable
private fun DashBoardSelection(
    modifier: Modifier = Modifier,
    selectedTab: (Int) -> Unit,
) {
    val list = listOf("Order History", "My Listings")
    var selected by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        SpacerWidth(20.dp)
        list.forEachIndexed { index, s ->
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

@Composable
fun SpacerWidth(
    width: Dp = 10.dp
) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun SpacerHeight(
    height: Dp = 10.dp
) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
private fun SelectionRow(
    modifier: Modifier = Modifier,
    selected: Boolean,
    index: Int,
    title: String,
    onValueChange: (Int) -> Unit,
    onClick: (Int) -> Unit,
) {
    Button(
        onClick = {
            onClick(index)
            onValueChange(index)
        }, modifier = modifier
            .padding(end = 8.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (selected) White else Black,
            containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent
        ),
        border = if (selected) BorderStroke(
            1.dp,
            NFTBorderColor.copy(alpha = 0.33f)
        ) else BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(16.dp)
    ) {
        androidx.compose.material3.Text(
            text = title, style = MaterialTheme.typography.labelMedium

        )
    }
}