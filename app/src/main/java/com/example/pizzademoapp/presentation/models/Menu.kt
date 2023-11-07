package com.example.pizzademoapp.presentation.models

import com.example.pizzademoapp.domain.models.GoodItem

sealed class Menu() {
    data class MenuItems(val items : List<GoodItem>, val networkError : Boolean = false) : Menu()
    object Initial : Menu()
    object NoInternetError  : Menu()
    object NetworkError  : Menu()
}
