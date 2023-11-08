package com.example.pizzademoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class ItemsDTO(
    @SerializedName("idMeal") val id: Int,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strInstructions") val description: String,
    @SerializedName("strMealThumb") val image: String,
)