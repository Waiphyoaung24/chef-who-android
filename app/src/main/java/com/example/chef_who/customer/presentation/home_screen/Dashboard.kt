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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    keyword: String

) {
    Column {
        Spacer(Modifier.height(16.dp))
        TopBar("Wai Phyo Aung", "Hi", navigateToCart = navigateToCart, mCartItems)
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
                        mCatIds = mCatIds
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
private fun ShowHorizontalElements(item: Dashboard.Item, mCatIds: List<Category>) {
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
                    mCatIds = mCatIds[index].name
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
fun TopBar(userName: String, value: String, navigateToCart: () -> Unit, cartItems: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (value.isNotEmpty()) {
            Text(
                text = "Hello $userName!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

        } else {
            val login = "Login"
            val andText = " / "
            val termsAndCondition = "Register"
            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    pushStringAnnotation(tag = login, annotation = login)
                    append(login)

                }
                append(andText)
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    pushStringAnnotation(tag = termsAndCondition, annotation = termsAndCondition)
                    append(termsAndCondition)
                }
            }
            Text(
                text = annotatedString,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold

            )
        }

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