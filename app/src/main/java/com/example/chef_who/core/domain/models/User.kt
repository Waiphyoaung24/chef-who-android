package com.example.chef_who.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var id: String,
    var first_name: String,
    var last_name: String,
    var email: String,
    var password: String,
    var isSeller : Boolean = false
)