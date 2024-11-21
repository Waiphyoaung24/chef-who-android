package com.example.chef_who.customer.presentation.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chef_who.customer.domain.Food

@Composable
fun FoodEachRow(
    food: Food,
    navigateToDetail: (String) -> Unit,
    onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .clickable(onClick = onClick)
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = food.image),
                    contentDescription = "",
                    Modifier.size(264.dp)
                )
                Text22_600(
                    text = food.name,
                    color = Color.Black,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text17_700(text = "$${food.price}", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }

}


@Composable
fun Text22_600(
    text: String,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
fun Text17_700(
    text: String,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}
