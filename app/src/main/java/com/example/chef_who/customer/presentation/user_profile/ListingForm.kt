package com.example.chef_who.customer.presentation.user_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chef_who.core.domain.models.FoodMenu
import com.example.chef_who.core.presentation.auth.MyTextField
import com.example.chef_who.customer.domain.Food

@Composable
fun ListingForm(modifier: Modifier = Modifier, food: FoodMenu, onTextChange: (String, String) -> Unit,onClick:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyTextField(
            "Menu Name",
            food.name,
            onTextChange = onTextChange,
            imageVector = Icons.Default.Email
        )


        MyTextField(
            "Image",
            food.image,
            onTextChange = onTextChange,
            imageVector = Icons.Default.Email
        )
        MyTextField(
            "Price",
            food.price.toString(),
            onTextChange = onTextChange,
            imageVector = Icons.Default.Email,
        )

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Text("Add Menu Item", style = MaterialTheme.typography.labelMedium, color = Color.White)
        }
    }
}