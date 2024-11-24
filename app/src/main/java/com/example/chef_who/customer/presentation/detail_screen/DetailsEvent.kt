package com.example.chef_who.customer.presentation.detail_screen

import com.example.chef_who.core.domain.models.Category

sealed class DetailsEvent {

    data class InsertDeleteArticle(val article: Category) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()

}