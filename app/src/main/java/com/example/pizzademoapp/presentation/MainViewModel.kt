package com.example.pizzademoapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzademoapp.domain.models.GoodItem
import com.example.pizzademoapp.domain.usecases.GetBannersUseCase
import com.example.pizzademoapp.domain.usecases.GetGoodsUseCase
import com.example.pizzademoapp.domain.usecases.GetMenuDirectoriesUseCase
import com.example.pizzademoapp.presentation.models.Advertisement
import com.example.pizzademoapp.presentation.models.Menu
import com.example.pizzademoapp.presentation.models.MenuDerictoryItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainViewModel(
    private val getFood: GetGoodsUseCase,
    private val getAdds: GetMenuDirectoriesUseCase,
    private val getBanners: GetBannersUseCase
) : ViewModel() {

    val menuDirectoriesHorizontal = MutableStateFlow<List<MenuDerictoryItem>>(listOf())
    val advertisement = MutableStateFlow<Advertisement>(Advertisement.Initial)

    private val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
        when (throwable) {
            is android.system.ErrnoException, is java.net.UnknownHostException -> {
                viewModelScope.launch {
                    // items.emit(Menu.NoInternetError)
                    Log.e("eeeee", "Menu.NoInternetError")
                }
            }

            else -> {
                viewModelScope.launch {
                    // items.emit(Menu.NetworkError)
                    Log.e("eeeee", "Menu.NetworkError")
                }
            }

        }
    }

    private var foodItems = ArrayList<GoodItem>()
    private val menuItemsTrigger = MutableSharedFlow<String>(replay = 1)
    val menuItems: StateFlow<Menu> = flow {
        menuItemsTrigger.emit("meat")
        menuItemsTrigger.collect { value ->

        val result = getFood.execute(value) //слазить в сеть за списком

          val temp = ArrayList<GoodItem>()
            temp.addAll(result)
            foodItems = temp

            emit(Menu.MenuItems(items = foodItems))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Menu.MenuItems(items = foodItems)
    )


    //TODO
    fun requestMenuItemsById(strID: String) {
        viewModelScope.launch(exceptionHandler) {
            menuItemsTrigger.emit(strID)
        }

    }

    //TODO
    fun loadAd() {
        viewModelScope.launch {
            val items = getAdds.execute()
            menuDirectoriesHorizontal.emit(items)

            val banners = getBanners.execute()
            advertisement.emit(Advertisement.Ads(items = banners))
            //  requestMenuItemsById("meat")

        }
    }
}