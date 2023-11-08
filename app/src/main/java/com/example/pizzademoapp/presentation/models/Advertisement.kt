package com.example.pizzademoapp.presentation.models
sealed class Advertisement {
    data class Ads(val items: List<Banner>) : Advertisement()
    object Initial : Advertisement()
}