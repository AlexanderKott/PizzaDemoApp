package com.example.pizzademoapp.domain.usecases

import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.presentation.models.Banner
class GetBannersUseCase(private val repo: FoodRepository) {
    suspend fun execute(): List<Banner> {
        return repo.getBanners()
    }
}


