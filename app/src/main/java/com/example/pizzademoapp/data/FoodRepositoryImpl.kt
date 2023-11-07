package com.example.pizzademoapp.data

import com.example.pizzademoapp.R
import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.domain.models.GoodItem
import com.example.pizzademoapp.presentation.models.Banner
import com.example.pizzademoapp.presentation.models.MenuDerictoryItem
import com.example.pizzademoapp.presentation.models.MenuDirectoryID

class FoodRepositoryImpl(val netStorage : NetStorage
) : FoodRepository {


    //retrofit
    override suspend fun getItemsById(param: String): List<GoodItem> {
      val items = netStorage.getDishesByName(param)
        return items
    }


 //Banners можно захардкодить (по условиям тз)
    override suspend fun getBanners(): List<Banner> {
        val list = mutableListOf<Banner>().apply {
            add(Banner(R.drawable.b2, 1))
            add(Banner(R.drawable.b1, 2))
            add(Banner(R.drawable.b3, 3))
            add(Banner(R.drawable.b4, 4))

        }
        return list
    }


    //Banners можно захардкодить (по условиям тз)
    override suspend fun getMenuDirectories(): List<MenuDerictoryItem> {
        val list = mutableListOf<MenuDerictoryItem>().apply {
            add(MenuDerictoryItem("Мясо", MenuDirectoryID.MEAT))
            add(MenuDerictoryItem("Рыба", MenuDirectoryID.FISH))
            add(MenuDerictoryItem("Блинчики", MenuDirectoryID.PANCAKES))
            add(MenuDerictoryItem("Салаты", MenuDirectoryID.SALAD))
            add(MenuDerictoryItem("Супы", MenuDirectoryID.SOUP))
        }
        return list
    }
}