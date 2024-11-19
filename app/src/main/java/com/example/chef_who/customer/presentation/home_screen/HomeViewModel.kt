package com.example.chef_who.customer.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.chef_who.core.domain.usecases.meals.MealsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealsUseCases: MealsUseCases
) : ViewModel() {

    val meals = mealsUseCases.getMeals(
        sources = listOf("techcrunch")
    ).cachedIn(viewModelScope)

}