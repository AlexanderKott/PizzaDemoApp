package com.example.pizzademoapp.di

import com.example.pizzademoapp.data.FoodRepositoryImpl
import com.example.pizzademoapp.data.NetStorage
import com.example.pizzademoapp.data.NetStorageImpl
import com.example.pizzademoapp.domain.FoodRepository
import com.example.pizzademoapp.domain.usecases.GetBannersUseCase
import com.example.pizzademoapp.domain.usecases.GetGoodsUseCase
import com.example.pizzademoapp.domain.usecases.GetMenuDirectoriesUseCase
import com.example.pizzademoapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NetStorage> { NetStorageImpl() }
    factory { GetGoodsUseCase(get()) }
    factory { GetMenuDirectoriesUseCase(get()) }
    factory { GetBannersUseCase(get()) }
    single<FoodRepository> { FoodRepositoryImpl(get()) }
    viewModel { MainViewModel(get(), get(), get()) }
}


