package com.example.pizzademoapp.presentation.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzademoapp.presentation.MainViewModel
import com.example.pizzademoapp.presentation.models.Advertisement
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopAdvertisement(viewModel: MainViewModel = koinViewModel()) {
    val ads by viewModel.advertisement.collectAsState()
    val adToDisplay = ads

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        when (adToDisplay) {
            is Advertisement.Ads -> {
                items(adToDisplay.items) { item ->
                    Card(
                        shape = RoundedCornerShape(size = 8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp
                        ),
                        modifier = Modifier.clickable { }

                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(100.dp)
                                .width(200.dp),
                            painter = painterResource(id = item.imageID),
                            contentDescription = null
                        )
                    }
                }
            }

            else -> {}
        }
    }

}