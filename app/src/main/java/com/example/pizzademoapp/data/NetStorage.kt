package com.example.pizzademoapp.data

import com.example.pizzademoapp.domain.models.GoodItem



interface  NetStorage {
    suspend fun getDishesByName(name: String): MutableList<GoodItem>
}