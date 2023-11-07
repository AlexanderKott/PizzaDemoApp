package com.example.pizzademoapp.domain

import com.example.pizzademoapp.domain.models.GoodItem
import com.example.pizzademoapp.presentation.models.Banner
import com.example.pizzademoapp.presentation.models.MenuDerictoryItem


interface FoodRepository {
  suspend fun getItemsById(param : String): List<GoodItem>
  suspend fun getMenuDirectories(): List<MenuDerictoryItem>
  suspend fun getBanners(): List<Banner>
}