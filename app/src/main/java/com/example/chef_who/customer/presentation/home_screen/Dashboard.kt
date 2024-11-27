package com.example.chef_who.customer.presentation.home_screen

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
import androidx.compose.material.Divider
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.presentation.update.components.ShowVerticalDivider


@Composable
fun ShowDashboard(
    mDataLayout: List<Dashboard.Item>,
    mSellerList: List<Seller>,
    mCatIds: List<Category>,
    mCartItems: Int,
    navigateToCart: () -> Unit,
    navigateToMenuList: (String) -> Unit,
    onSearch: () -> Unit,
    onValueChanged: (String) -> Unit,
    keyword: String,
    onCatItemClick:(String)->Unit,
    userName: String,

) {
    Column {
        Spacer(Modifier.height(16.dp))
        TopBar(userName, navigateToCart = navigateToCart, mCartItems)
        SearchBar(
            text = keyword,
            onValueChange = onValueChanged,
            readOnly = false,
            onSearch = onSearch
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding))
        ) {

            itemsIndexed(items = mDataLayout) { index, item ->
                when (item.viewType) {
                    "horizontalScroll" -> ShowHorizontalElements(
                        item = item,
                        mCatIds = mCatIds,
                        onItemClick = onCatItemClick
                    )
                }

                if (index != item.data.size) Spacer(modifier = Modifier.height(10.dp))

            }
            item {
                ShowVerticalElements(
                    navigateToMenuList = navigateToMenuList,
                    mSellerList = mSellerList
                )
            }

        }

    }
}

@Composable
private fun ShowHorizontalElements(
    item: Dashboard.Item,
    mCatIds: List<Category>,
    onItemClick: (String) -> Unit
) {
    print(item)
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
                    item = data,
                    mCatIds = mCatIds[index].name,
                    onItemClick = ({
                        onItemClick((index+1).toString())
                    })
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
    mSellerList: List<Seller>,
    navigateToMenuList: (String) -> Unit,
) {

    ShowHeader(
        title = "Popular Restaurants",
        hasMore = true
    )

    mSellerList.forEachIndexed { index, data ->

        ShowRestaurantElement(
            data = data,
            navigateToMenuList = navigateToMenuList,
            onClick = {
                navigateToMenuList(data.id.toString())
            }

        )

    }
}

@Composable
fun TopBar(userName: String, navigateToCart: () -> Unit, cartItems: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
            Text(
                text = "Chef-Who",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

        BadgedBox(badge = { Badge { Text(cartItems.toString()) } }) {

            IconButton(onClick = navigateToCart) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}

@Composable
fun AuthRequiredScreen(
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In Required",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Please sign in or register to continue.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onSignInClick) {
            Text("Sign In", style = MaterialTheme.typography.labelMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onRegisterClick) {
            Text("Register", style = MaterialTheme.typography.labelMedium)
        }
    }
}