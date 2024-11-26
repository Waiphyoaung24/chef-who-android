package com.example.chef_who.customer.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.example.chef_who.customer.presentation.update.components.LoadImage
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.customer.domain.Dashboard


@Composable
fun ShowRestaurantElement(
    data : Seller,
    navigateToMenuList: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding))
            .clickable(onClick = onClick)
    ) {
        RestaurantImage(url = data.imageUrl)
        Spacer(Modifier.width(24.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding))
                .align(Alignment.CenterVertically)
        ) {
            RestaurantInfo(item = data)
        }

    }
}

@Composable
private fun RestaurantImage(url: String) {
    LoadImage(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(5.dp)),
        image = url
    )
}

@Composable
private fun RestaurantInfo(item: Seller) {
    val title = item.title ?: "Name"
    val subTitle = item.subTitle ?: "Caption"
    val rating = "${item.meta.rating}" ?: "4.0"
    val reviewCount = "${item.meta.reviewCount}" ?: "500+ reviews"


    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium
    )


    Text(
        text = subTitle,
        style = MaterialTheme.typography.labelMedium
    )


    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            contentDescription = "",
            painter = painterResource(id = R.drawable.ic_round_star_24),
            tint = Color(0xFFF5CE47)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = buildAnnotatedString {
                append(rating)
                pushStyle(SpanStyle(color = Color.Gray))
                append(" ( $reviewCount reviews)")
                pop()
            }, style = MaterialTheme.typography.labelSmall
        )


    }
    Box(Modifier.padding(top = 4.dp)) {
        Text(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(100.dp))
                .padding(horizontal = 5.dp),
            text = stringResource(id = R.string.free_delivery),
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }

}
