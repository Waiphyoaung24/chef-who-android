package com.example.chef_who.core.domain.models

import com.example.chef_who.customer.domain.DashboardAction
import kotlinx.serialization.Serializable

data class Seller(
    val id: String,
    val imageUrl: String,
    val title: String? = null,
    val subTitle: String? = null,
    val meta: Meta
)
