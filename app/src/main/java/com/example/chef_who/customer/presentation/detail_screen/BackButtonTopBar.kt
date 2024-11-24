package com.example.chef_who.customer.presentation.detail_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chef_who.R
import com.example.chef_who.ui.theme.ChefWhoTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopBar(
    onBackClick: () -> Unit,
) {
    IconButton(onClick = onBackClick) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
        )
    }

}

