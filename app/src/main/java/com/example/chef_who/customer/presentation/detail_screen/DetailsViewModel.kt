package com.example.chef_who.customer.presentation.detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chef_who.core.domain.models.Article
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.InsertDeleteArticle ->
                viewModelScope.launch {
                    val article = mealsUseCases.selectSingleMeal(event.article.url)
                    if (article == null) {
                        insertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }

                }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }

    }

    private suspend fun deleteArticle(article: Article) {
        mealsUseCases.deleteMeals(article = article)
        sideEffect = "Article deleted"
    }

    private suspend fun insertArticle(article: Article) {
        mealsUseCases.insertMeals(article = article)
        sideEffect = "Article Inserted"
    }
}