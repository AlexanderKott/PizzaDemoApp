package com.example.pizzademoapp.presentation.ui.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pizzademoapp.R
import com.example.pizzademoapp.presentation.MainViewModel
import com.example.pizzademoapp.presentation.models.Menu
import org.koin.androidx.compose.koinViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoodsList(paddingValues: PaddingValues, viewModel: MainViewModel = koinViewModel()) {
    val items by viewModel.menuItems.collectAsState()
    val dishes = items

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .padding(paddingValues)
    ) {

        when (dishes) {
            is Menu.Initial -> {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.nothing_to_display)
                    )
                }
            }

            is Menu.NoInternetError -> {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.no_internet_connection)
                    )
                }
            }

            is Menu.NetworkError -> {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.network_error)
                    )
                }
            }

            is Menu.MenuItems -> {
                if (dishes.items.isEmpty()) {
                    item {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = stringResource(R.string.list_is_empty)
                        )
                    }
                } else {
                    items(dishes.items) { item ->
                        Card(
                            onClick = { /*...*/ },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 1.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                            Row {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(item.picture)
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(R.drawable.placeholder),
                                    error = painterResource(R.drawable.placeholder),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(140.dp)
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )

                                Column {
                                    Text(
                                        text = item.name,
                                        modifier = Modifier
                                            .padding(2.dp)
                                            .fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = item.description,
                                        modifier = Modifier
                                            .padding(start = 7.dp)
                                            .fillMaxWidth(),
                                        color = Color.Gray,
                                        lineHeight = 15.sp,
                                        fontSize = 15.sp,
                                        maxLines = 4,
                                        textAlign = TextAlign.Left,
                                        overflow = TextOverflow.Ellipsis
                                    )

                                    Row {
                                        Spacer(
                                            modifier = Modifier
                                                .width(1.dp)
                                                .weight(1f)
                                        )
                                        OutlinedButton(
                                            modifier = Modifier.defaultMinSize(
                                                minWidth = 3.dp,
                                                minHeight = 3.dp
                                            ),
                                            onClick = { /*TODO*/ },
                                            shape = CutCornerShape(2.dp),
                                            border = BorderStroke(width = 1.dp, color = Color.Red),
                                            contentPadding = PaddingValues(7.dp),
                                            colors = ButtonDefaults.textButtonColors(
                                                contentColor = Color.Red
                                            ),
                                            ) {
                                            Text(text = "от ${item.price} руб")
                                        }
                                        Spacer(modifier = Modifier.width(17.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
