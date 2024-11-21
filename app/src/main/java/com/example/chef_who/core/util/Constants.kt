package com.example.chef_who.core.util

import com.example.chef_who.core.domain.models.Brand

object Constants {

    const val USER_SETTINGS = "user_settings"
    const val APP_ENTRY = "app_entry"

    //const val BASE_URL = "https://newsapi.org/v2/"
    const val BASE_URL = "http://10.0.2.2:3000/"
//    const val BASE_URL = "https://gist.githubusercontent.com/vipulasri/8bd2115e50fd73272ea8de08cd54b9d5/raw/"

    //

    const val API_KEY = "cf2ec1506e6c4ce8a76b2806a18a5eed"
    const val KEY_BACKGROUND = "background"
    const val KEY_SHOE_TITLE = "shoe-title"
    const val KEY_FAVOURITE_ICON = "favourite-icon"
    const val KEY_SHOE_IMAGE = "shoe-image"

    val brandList = listOf(
        Brand(id = 1, name = "Nike", country = "USA"),
        Brand(id = 2, name = "Adidas", country = "Germany"),
        Brand(id = 3, name = "Puma", country = "Germany"),
        Brand(id = 4, name = "Reebok", country = "USA"),
        Brand(id = 5, name = "Under Armour", country = "USA"),
        Brand(id = 6, name = "Asics", country = "Japan")
    )

}