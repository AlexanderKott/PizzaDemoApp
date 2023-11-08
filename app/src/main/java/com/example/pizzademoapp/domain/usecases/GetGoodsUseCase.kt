package com.example.pizzademoapp.domain.usecases

import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.domain.models.GoodItem

class GetGoodsUseCase(private val repo: FoodRepository) {
    suspend fun execute(param: String): List<GoodItem> {
        return repo.getItemsById(param)
    }
}