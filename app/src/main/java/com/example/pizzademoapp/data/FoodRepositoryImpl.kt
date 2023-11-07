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
    override suspend fun getBanners(): List<Banner> = listOf(
             Banner(R.drawable.b2, 1),
             Banner(R.drawable.b1, 2),
             Banner(R.drawable.b3, 3),
             Banner(R.drawable.b4, 4),
    )



    //Banners можно захардкодить (по условиям тз)
    override suspend fun getMenuDirectories(): List<MenuDerictoryItem> = listOf(
             MenuDerictoryItem("Мясо", MenuDirectoryID.MEAT) ,
             MenuDerictoryItem("Рыба", MenuDirectoryID.FISH),
             MenuDerictoryItem("Блинчики", MenuDirectoryID.PANCAKES),
             MenuDerictoryItem("Салаты", MenuDirectoryID.SALAD) ,
             MenuDerictoryItem("Супы", MenuDirectoryID.SOUP))


    }
