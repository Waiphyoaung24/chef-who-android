package com.example.chef_who.customer.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.navigation.Route
import com.example.chef_who.core.presentation.common.FoodList
import com.example.chef_who.core.presentation.common.MealList
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.domain.listOfFood
import com.example.chef_who.ui.theme.Dimens.MediumPadding1

@Composable
fun MenuListScreen(
    title: String,
    food: List<Food>,
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 64.dp, start = MediumPadding1, end = MediumPadding1)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        FoodList(modifier = Modifier, food, navigateToDetail = navigateToDetail)
    }
}