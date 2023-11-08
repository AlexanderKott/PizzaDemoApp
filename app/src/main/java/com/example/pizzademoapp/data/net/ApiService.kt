package com.example.pizzademoapp.data.net

import com.example.pizzademoapp.data.dtos.MealDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php?")
    suspend fun getDishesByName(@Query("s") name: String): MealDTO
}