package com.example.chef_who.core.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.chef_who.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Discover Home-Cooked Delights",
        description = "Explore a world of delicious, home-cooked meals made with love, just a tap away.",
        image = R.drawable.img_onboard1
    ),
    Page(
        title = "Effortless Ordering",
        description = "Say goodbye to missed orders and delays. Enjoy a seamless and efficient food ordering experience.",
        image = R.drawable.img_onboard2
    ),
    Page(
        title = "Support Local Home Chefs",
        description = "Connect with talented home chefs and savor meals crafted with passion and care.",
        image = R.drawable.img_onboard3
    )
)