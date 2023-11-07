package com.example.pizzademoapp.presentation.ui.layout

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SelectableChipBorder
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizzademoapp.presentation.MainViewModel
import com.example.pizzademoapp.presentation.models.MenuDirectoryID
import org.koin.androidx.compose.koinViewModel



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DirectoryItems(viewModel: MainViewModel = koinViewModel()) {

   val context = LocalContext.current
   val items by viewModel.menuDirectoriesHorizontal.collectAsState()

    var selectedItem by rememberSaveable { mutableStateOf(MenuDirectoryID.MEAT) }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(items) { item ->
            InputChip(

                colors = InputChipDefaults.inputChipColors(
                    containerColor = Color.White,
                   selectedContainerColor = Color(0xFFE91E63),

                ),

                elevation = InputChipDefaults.inputChipElevation(
                    defaultElevation = 3.dp
                )
                ,
                border  = InputChipDefaults.inputChipBorder(
                    borderColor = Color.White
                ),


                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item.itemID == selectedItem),
                onClick = {
                    selectedItem = item.itemID
                    viewModel.requestMenuItemsById(selectedItem.id)
                },
                label = {
                    Text(text = item.name, color = Color(0xFF771738))
                }
            )
        }
    }
}