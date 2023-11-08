package com.example.pizzademoapp.presentation.ui.layout

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.pizzademoapp.presentation.ui.layout.bottomBar.PizzaBottomBar
import com.example.pizzademoapp.presentation.ui.pizzaToolBar.PizzaToolbar
import com.example.pizzademoapp.presentation.ui.pizzaToolBar.rememberToolbarScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollBehavior = rememberToolbarScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = { PizzaBottomBar() },
        topBar = {
            PizzaToolbar(
                topContent = { TopMenu() },
                navigationIcon = null,
                centralContent = null,
                additionalContent = { DirectoryItems() },
                scrollBehavior = scrollBehavior,
                collapsingTitle = null,
                collapsedContent = { TopAdvertisement() }
            )
        }
    ) { paddingValues ->
        GoodsList(paddingValues)
    }
}