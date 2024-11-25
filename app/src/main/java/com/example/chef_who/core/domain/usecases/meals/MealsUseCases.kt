package com.example.chef_who.core.domain.usecases.meals

data class MealsUseCases(
    val mHomeType: GetHomeType,
    val searchMeals: SearchMeals,
    val deleteMeals: DeleteMeals,
    val insertMeals: InsertMeals,
    val selectMeals: SelectMeals,
    val selectSingleMeal: SelectSingleMeal,
    val getCategoryIds: GetCategoryIds,
    val getMenuList: GetMenuList,
    val getCartIds: GetCartList,
    val createOrder: CreateOrder,
    val getSellerList: GetSellerList,
)
