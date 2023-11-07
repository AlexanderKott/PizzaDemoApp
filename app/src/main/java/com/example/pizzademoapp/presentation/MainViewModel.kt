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
import com.example.pizzademoapp.presentation.models.MenuDirectoryID
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val getFood: GetGoodsUseCase,
    private val getAdds: GetMenuDirectoriesUseCase,
    private val getBanners: GetBannersUseCase
) : ViewModel() {

    private var foodItems = ArrayList<GoodItem>()
    private val menuItemsTrigger = MutableSharedFlow<String>(replay = 1)
    val menuItems: StateFlow<Menu> = flow {
        menuItemsTrigger.emit(MenuDirectoryID.MEAT.id)
        menuItemsTrigger.collect { value ->
            try {
                val result = getFood.execute(value)
                foodItems.clear()
                foodItems.addAll(result)
                emit(Menu.MenuItems(items = foodItems.toList()) as Menu)

            } catch (e : Throwable){
                when (e) {
                    is android.system.ErrnoException, is java.net.UnknownHostException -> {
                        emit(Menu.NoInternetError)
                    }
                    else -> {
                        emit(Menu.NetworkError)
                    }
                }
            }

        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Menu.MenuItems(items = listOf())
        )

    private val menuDirectoriesHorizontalTrigger = MutableSharedFlow<Unit>(replay = 1)
    val menuDirectoriesHorizontal: StateFlow<List<MenuDerictoryItem>> = flow {
        menuDirectoriesHorizontalTrigger.emit(Unit)
        menuDirectoriesHorizontalTrigger.collect {
            val items = getAdds.execute()
            emit(items)
        }
    } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )


    private val advertisementTrigger = MutableSharedFlow<Unit>(replay = 1)
    val advertisement: StateFlow<Advertisement> = flow {
        advertisementTrigger.emit(Unit)
        advertisementTrigger.collect {
            val banners = getBanners.execute()
            emit(Advertisement.Ads(items = banners))
        }
    } .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Advertisement.Initial
    )


    fun requestMenuItemsById(strID: String) {
        viewModelScope.launch {
            Log.e("eeeee", "trigger $strID")
            menuItemsTrigger.emit(strID)
        }
    }
}