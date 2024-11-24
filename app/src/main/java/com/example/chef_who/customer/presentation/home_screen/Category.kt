package com.example.chef_who.customer.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chef_who.core.domain.models.Category
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.presentation.update.components.LoadImage


@Composable
fun ShowCategoryElement(item: Dashboard.Item.SubItem,mCatIds : String) {
    Column {
        Spacer(modifier = Modifier.height(5.dp)) // added to support space for header
        CategoryImage(item = item)
        Spacer(modifier = Modifier.height(5.dp))
        CategoryInfo(
            title = mCatIds,
            subTitle = item.subTitle
        )
    }
}

@Composable
private fun CategoryImage(item: Dashboard.Item.SubItem) {
    val bgColor = item.meta?.bgColor?.let { color ->
        getColor(color)
    } ?: Color.Blue

    Box(
        modifier = Modifier
            .size(70.dp)
            .background(bgColor, shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        LoadImage(
            image = item.imageUrl,
            tint = Color.White
        )
    }
}

@Composable
private fun CategoryInfo(title: String?, subTitle: String?) {
    title?.let {

        Text(
            text = it,
            style = MaterialTheme.typography.labelSmall
        )

    }
    subTitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.labelSmall
        )

    }
}

fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}