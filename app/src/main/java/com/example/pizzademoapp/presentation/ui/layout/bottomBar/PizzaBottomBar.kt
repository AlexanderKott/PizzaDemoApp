package com.example.pizzademoapp.presentation.ui.layout.bottomBar

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.pizzademoapp.R

@Composable
fun PizzaBottomBar() {
    val bottomBarItemsList = mutableListOf<BottomBarItem>()
    bottomBarItemsList.add(
        BottomBarItem(
            icon = Icons.Default.Home,
            name = stringResource(R.string.Menu)
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            icon = Icons.Default.Person,
            name = stringResource(R.string.Profile)
        )
    )
    bottomBarItemsList.add(
        BottomBarItem(
            icon = Icons.Default.ShoppingCart,
            name = stringResource(R.string.Cart)
        )
    )

    val context = LocalContext.current
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        bottomBarItemsList.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}



