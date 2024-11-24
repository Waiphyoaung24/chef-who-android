package com.example.chef_who.customer.presentation.home_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chef_who.R

@Composable
fun ShowHeader(title: String, hasMore: Boolean) {
    Row(
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = R.dimen.padding),
            vertical = 5.dp
        )
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )


        if (hasMore) {
            TextButton(
                onClick = {

                }
            ) {
                Text(
                    modifier = Modifier,
                    text = "View All",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
                )
            }
        }
    }
}


@Preview
@Composable
private fun PreviewHeader() {
    ShowHeader(title = "Popular Restaurants Near You", hasMore = true)
}