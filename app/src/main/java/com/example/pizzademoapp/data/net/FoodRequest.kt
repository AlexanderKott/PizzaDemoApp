package com.example.pizzademoapp.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val SITE = "https://www.themealdb.com/api/json/v1/1/"

object FoodRequest {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(SITE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    val apiService: ApiService = retrofit.create()


}