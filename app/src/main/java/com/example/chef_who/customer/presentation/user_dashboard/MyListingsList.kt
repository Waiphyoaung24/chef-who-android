package com.example.chef_who.customer.presentation.user_dashboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.OrderActiveResponse
import com.example.chef_who.core.domain.models.OrderHistoryResponse


@Composable
fun ListItemForDashBoard(
    modifier: Modifier = Modifier,
    orderHistory: OrderHistoryResponse,

    ) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        OrderHistoryItem(modifier = Modifier, orderHistory)
    }
}

@Composable
fun ListItemForActiveDashBoard(
    modifier: Modifier = Modifier,
    activeOrderResponse: OrderActiveResponse,
    onSellerClick: (String,String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        ActiveOrderItem(modifier = Modifier, activeOrderResponse, onSellerClick)
    }
}

@Composable
fun OrderHistoryItem(modifier: Modifier = Modifier, data: OrderHistoryResponse) {
    val VoiletColor = Color(0XFF577CFF)

    Row(verticalAlignment = Alignment.CenterVertically) {
        AppIcon(
            icon = R.drawable.img_seller, modifier = Modifier
                .size(36.dp)
        )
        SpacerWidth(12.dp)

        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = data.seller_title,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )

        }
        Text(
            "GBP ${data.item_price}",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
    SpacerHeight(16.dp)
    Row(modifier = Modifier.padding(start = 8.dp)) {
        AppIcon(icon = R.drawable.calendar, tint = Color.Gray)
        SpacerWidth()
        Text(
            text = ("25 Nov 2024, 10:39"),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Light,
                color = Color.Gray,
            ),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun ActiveOrderItem(
    modifier: Modifier = Modifier,
    data: OrderActiveResponse,
    onSellerClick: (String,String) -> Unit
) {
    val VoiletColor = Color(0XFF577CFF)

    Row(verticalAlignment = Alignment.CenterVertically) {
        AppIcon(
            icon = R.drawable.person, modifier = Modifier
                .size(36.dp)
        )
        SpacerWidth(8.dp)

        Column(modifier = Modifier.weight(0.5f)) {
            Text(
                text = data.customer_name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )

            Text(
                text = data.menu_item_name,
                style = MaterialTheme.typography.labelMedium

            )
        }
        AppIcon(
            imageVector = Icons.Default.Check,
            tint = Color.White,
            modifier = Modifier
                .size(22.dp)
                .clickable(onClick = { onSellerClick.invoke("Accepted",data.order_item_id) })
                .drawBehind {
                    drawCircle(
                        color = VoiletColor,
                        radius = 40f
                    )
                })
        SpacerWidth(width = 8.dp)
        AppIcon(
            imageVector = Icons.Default.Close,
            tint = Color.White,
            modifier = Modifier
                .size(22.dp)
                .clickable(onClick = { onSellerClick.invoke("Declined",data.order_item_id) })
                .drawBehind {
                    drawCircle(
                        color = Color.Red,
                        radius = 40f
                    )
                })
    }
    SpacerHeight(16.dp)
    HorizontalDivider(color = Color.DarkGray, thickness = 1.dp)
    SpacerHeight(16.dp)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f)) {
            Text(
                text = "Quantity : ${data.quantity}",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Gray,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        AppIcon(imageVector = Icons.Default.Call, tint = Color.White,
            modifier = Modifier
                .size(20.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.Green,
                        radius = 40f
                    )
                })
    }
}

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    imageVector: ImageVector? = null,
    tint: Color = Color.Unspecified
) {
    icon?.let {
        Icon(
            painter = painterResource(id = it),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )
    }
    imageVector?.let {
        Icon(imageVector = it, contentDescription = null, modifier = modifier, tint = tint)
    }
}