package com.example.chef_who.core.presentation.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.chef_who.customer.domain.Food
import com.example.chef_who.customer.presentation.home_screen.FoodEachRow
import com.example.chef_who.ui.theme.Dimens.ExtraSmallPadding2
import com.example.chef_who.ui.theme.Dimens.MediumPadding1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    foods: List<Food>,
    navigateToDetail: (Food) -> Unit,
) {
    val pager = rememberPagerState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = foods.size,
            state = pager
        ) { index ->
            FoodEachRow(
                foods[index],
                navigateToDetail = navigateToDetail,
                onClick = { navigateToDetail(foods[index]) })
        }
        Spacer(Modifier.height(64.dp))
        HorizontalPagerIndicator(
            pagerState = pager,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
    }


}



@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ShimmerEffectCard(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}