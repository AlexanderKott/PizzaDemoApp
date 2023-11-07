package com.example.pizzademoapp.data

import com.example.pizzademoapp.data.dtos.MealDTO
import com.example.pizzademoapp.data.net.FoodRequest
import com.example.pizzademoapp.domain.models.GoodItem




class NetStorageImpl : NetStorage{

   override suspend fun getDishesByName(name: String): MutableList<GoodItem> {
        val response = FoodRequest.apiService.getDishesByName(name)
        return mapDTOtoGoodItem(response)
    }

    private fun mapDTOtoGoodItem(response: MealDTO): MutableList<GoodItem> {
        val result = mutableListOf<GoodItem>()
        for (i in response.items) {
            result.add(
                GoodItem(
                    id = i.id, name = i.name, picture = i.image, description = i.description,
                    price = "200"
                )
            )
        }
        return result
    }


}
