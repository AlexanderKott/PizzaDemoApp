package com.example.pizzademoapp.data.dtos

import com.google.gson.annotations.SerializedName


data class MealDTO(
    @SerializedName("meals") val items: List<ItemsDTO>,
)