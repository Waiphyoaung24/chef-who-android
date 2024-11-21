package com.example.chef_who.customer.presentation.detail_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.chef_who.R
import com.example.chef_who.customer.domain.Food


@Composable
fun DetailsScreen(
    data: Food,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CommonIconButton(icon = R.drawable.back, onClick = navigateUp)
                CommonIconButton(icon = R.drawable.heart)
            }

        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = data.image), contentDescription = "",
                    modifier = Modifier
                        .size(240.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = data.name, style = MaterialTheme.typography.displaySmall)
                Text(text = "$${data.price}", style = MaterialTheme.typography.displaySmall)

            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 30.dp)
            ) {
                Text(
                    text = "Delivery info",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = data.info,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 30.dp)
            ) {
                Text(
                    text = "Returned Policy",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = data.returnPolicy,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            CommonButton(
                text = "Add to cart",
                backgroundColor = MaterialTheme.colorScheme.primary,
                foregroundColor = Color.White
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


@Composable
fun CommonIconButton(
    icon: Int,
    tint: Color = Color.Unspecified,
    size: Dp = 24.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    IconButton(onClick = {
        onClick()
    }, modifier = modifier) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(size),
            tint = tint
        )
    }

}


@Composable
fun CommonButton(
    text: String,
    foregroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .clickable {
                    onClick()
                }
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = foregroundColor,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }

}
