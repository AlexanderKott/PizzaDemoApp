package com.example.pizzademoapp.presentation.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzademoapp.R

@Composable
fun TopMenu() {
    val listItems = arrayOf("Москва", "Санкт-Петербург", "Другие города...")

    var expanded by remember {
        mutableStateOf(false)
    }

    Row {

        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .defaultMinSize(minWidth = 3.dp, minHeight = 3.dp)
                .padding(start = 10.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            Text(
                text = listItems[0], Modifier.padding(start = 0.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.qr_code_scanner),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 10.dp, end = 22.dp)
                .size(29.dp)
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }
    ) {
        listItems.forEachIndexed { itemIndex, itemValue ->
            DropdownMenuItem(
                onClick = {
                    expanded = false
                },
                text = { Text(text = itemValue) }
            )
        }
    }
}
