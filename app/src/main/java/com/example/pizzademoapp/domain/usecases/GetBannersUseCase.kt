package com.example.pizzademoapp.domain.usecases

import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.domain.models.GoodItem
import com.example.pizzademoapp.presentation.models.Banner
import com.example.pizzademoapp.presentation.models.MenuDerictoryItem


class GetBannersUseCase(private val repo: FoodRepository) {
 suspend fun execute(): List<Banner> {
        return repo.getBanners()
    }
}


