package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.domain.models.Cart
import com.example.chef_who.core.domain.repository.MealsRepository

class GetCartList (
    private val mRepository: MealsRepository
){
    suspend operator fun invoke() : List<Cart>{
       return mRepository.getCartList()
    }
}