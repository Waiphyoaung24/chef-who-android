package com.example.chef_who.customer.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chef_who.customer.domain.Dashboard
import com.example.chef_who.customer.presentation.update.components.LoadImage

@Composable
fun ShowBannerElement(item: Dashboard.Item.SubItem) {
    Box(
        modifier = Modifier
            .size(width = 150.dp, height = 178.dp)
            .clip(RoundedCornerShape(5.dp)).padding(vertical = 5.dp)
    ) {
        BannerImage(url = item.imageUrl)
        item.title?.let {
            BannerText(
                modifier = Modifier.align(Alignment.BottomCenter),
                title = it
            )
        }
    }
}


@Composable
private fun BannerImage(url: String) {
    LoadImage(
        modifier = Modifier.fillMaxSize(),
        image = url
    )
}

@Composable
private fun BannerText(
    modifier: Modifier,
    title: String
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.3f))
            .padding(10.dp),
        text = title,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White,
            fontSize = 12.sp
        )
    )
}