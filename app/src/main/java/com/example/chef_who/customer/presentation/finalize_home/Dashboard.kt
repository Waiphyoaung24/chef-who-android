package com.example.chef_who.customer.presentation.finalize_home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chef_who.R
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.home_screen.SearchBar
import com.example.chef_who.customer.presentation.update.components.ShowVerticalDivider
import com.vipulasri.jetdelivery.ui.dashboard.ShowCategoryElement


@Composable
fun ShowDashboard(data: List<Dashboard.Item>, navigateToMenuList: (String) -> Unit) {
    Column {
        Spacer(Modifier.height(48.dp))
        TopBar("Wai Phyo Aung")
        SearchBar(text = "", onValueChange = {}, readOnly = false) {}
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding))
        ) {

            itemsIndexed(items = data) { index, item ->
                when (item.viewType) {
                    "horizontalScroll" -> ShowHorizontalElements(
                        item = item
                    )

                    "verticalScroll" -> ShowVerticalElements(
                        item = item, navigateToMenuList = navigateToMenuList
                    )
                }
                if (index != item.data.size) Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun ShowHorizontalElements(item: Dashboard.Item) {
    item.header?.let {
        ShowHeader(
            title = it.title,
            hasMore = it.hasMore
        )
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding))
    ) {
        itemsIndexed(item.data) { index, data ->
            when (data.viewType) {
                "bannerElement" -> ShowBannerElement(
                    item = data
                )

                "categoryElement" -> ShowCategoryElement(
                    item = data
                )

                else -> {
                    // do nothing
                }
            }
            if (index != item.data.size) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
private fun ShowVerticalElements(
    item: Dashboard.Item,
    navigateToMenuList: (String) -> Unit,
) {
    item.header?.let {
        ShowHeader(
            title = it.title,
            hasMore = it.hasMore
        )
    }
    item.data.forEachIndexed { index, data ->
        when (data.viewType) {
            "restaurantElement" -> ShowRestaurantElement(
                item = data,
                navigateToMenuList = navigateToMenuList,
                onClick = {
                    Log.d("test", "dashboard item clicked")
                    navigateToMenuList(data.title.toString())
                }

            )

            else -> {
                // do nothing
            }
        }
        if (index != item.data.size) ShowVerticalDivider()
    }
}

@Composable
fun TopBar(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Hello $userName!",
            style = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}