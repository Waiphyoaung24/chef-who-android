package com.example.chef_who.customer.presentation.cart_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.customer.presentation.detail_screen.BackButtonTopBar


@Composable
fun CartScreen(
    carts: List<Cart>,
    navigateUp: () -> Unit,
    createOrder: () -> Unit,
    navigateToDetails: (String) -> Unit,

    ) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BackButtonTopBar(onBackClick = navigateUp)
            BasicText(
                text = "My Shopping Cart",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        }
        if (carts.isEmpty()) {
            BasicText(
                text = "Your Cart is Empty",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )


        } else {
            LazyColumn(Modifier.defaultMinSize(420.dp)) {
                itemsIndexed(carts) { i, item ->
                    CartItem(modifier = Modifier,item)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFE0E0E0))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicText(
                        "Subtotal",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                    )
                    BasicText(
                        "£217.00",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable(onClick = createOrder)
                        .background(Color(0xFF3F51B5))
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        "Go to checkout",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun CartItem(modifier: Modifier = Modifier,item : Cart) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = item.img,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                .size(96.dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        text = item.name,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                    BasicText(
                        text = "£${item.price}",
                        maxLines = 1,
                        modifier = Modifier.padding(start = 16.dp),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }
                Spacer(Modifier.height(4.dp))
                BasicText(
                    text = "",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(
                            0xFF757575
                        )
                    )
                )

            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                BasicText(
                    text = "Quantity : ${item.quantity}",
                    style = MaterialTheme.typography.bodySmall
                )
                BasicText(
                    text = "Remove",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Red),
                    modifier = Modifier
                        .offset(x = 8.dp, y = 4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { /* TODO */ }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}