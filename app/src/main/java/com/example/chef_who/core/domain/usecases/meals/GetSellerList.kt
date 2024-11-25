package com.example.chef_who.core.domain.usecases.meals

import com.example.chef_who.core.domain.models.Seller
import com.example.chef_who.core.domain.repository.MealsRepository

class GetSellerList(private val mealsRepository: MealsRepository) {

    suspend operator fun invoke(): List<Seller> {
        return mealsRepository.getSellerList()
    }
}