package com.example.chef_who.customer.domain

import com.squareup.moshi.Json

data class Dashboard(
    val data: List<Item>
) {
    data class Item(
        val viewType: String,
        val header: Header? = null,
        val data: List<SubItem>
    ) {
        data class Header(
            val title: String,
            val hasMore: Boolean
        )

        data class SubItem(
            val viewType: String,
            val imageUrl: String,
            val title: String? = null,
            val subTitle: String? = null,
            val action: DashboardAction,
            val meta: Meta? = null
        ) {
            data class Meta(
                val bgColor: String? = null,
                val rating: String? = null,
                val reviewCount: String? = null,
                val hasFreeDelivery: Boolean
            )
        }
    }
}

data class DashboardAction(
    val type: Int,
    val value: String
)
