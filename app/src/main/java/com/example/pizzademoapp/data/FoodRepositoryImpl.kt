package com.example.pizzademoapp.data

import com.example.pizzademoapp.R
import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.domain.models.GoodItem
import com.example.pizzademoapp.presentation.models.Banner
import com.example.pizzademoapp.presentation.models.MenuDirectoryItem
import com.example.pizzademoapp.presentation.models.MenuDirectoryID

class FoodRepositoryImpl(
    private val netStorage: NetStorage
) : FoodRepository {

    //retrofit
    override suspend fun getItemsById(param: String): List<GoodItem> {
        return netStorage.getDishesByName(param)
    }

    //Banners можно захардкодить (по условиям тз)
    override suspend fun getBanners(): List<Banner> = listOf(
        Banner(R.drawable.b2, 1),
        Banner(R.drawable.b1, 2),
        Banner(R.drawable.b3, 3),
        Banner(R.drawable.b4, 4),
    )


    //Категории можно захардкодить (по условиям тз)
    override suspend fun getMenuDirectories(): List<MenuDirectoryItem> = listOf(
        MenuDirectoryItem("Мясо", MenuDirectoryID.MEAT),
        MenuDirectoryItem("Рыба", MenuDirectoryID.FISH),
        MenuDirectoryItem("Блинчики", MenuDirectoryID.PANCAKES),
        MenuDirectoryItem("Салаты", MenuDirectoryID.SALAD),
        MenuDirectoryItem("Супы", MenuDirectoryID.SOUP)
    )


}
