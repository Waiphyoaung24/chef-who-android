package com.example.chef_who.core.domain.usecases.meals

data class MealsUseCases(
    val getMeals: GetMeals,
    val mHomeType : GetHomeType,
    val searchMeals: SearchMeals,
    val deleteMeals: DeleteMeals,
    val insertMeals: InsertMeals,
    val selectMeals: SelectMeals,
    val selectSingleMeal: SelectSingleMeal
)
