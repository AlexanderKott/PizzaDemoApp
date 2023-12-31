package com.example.pizzademoapp.domain.usecases

import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.presentation.models.MenuDirectoryItem
class GetMenuDirectoriesUseCase(private val repo: FoodRepository) {
    suspend fun execute(): List<MenuDirectoryItem> {
        return repo.getMenuDirectories()
    }
}