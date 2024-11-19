package com.example.chef_who.customer.presentation.detail_screen

import com.example.chef_who.core.domain.models.Article

sealed class DetailsEvent {

    data class InsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()

}