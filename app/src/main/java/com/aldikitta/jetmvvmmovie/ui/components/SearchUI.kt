package com.aldikitta.jetmvvmmovie.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aldikitta.jetmvvmmovie.data.datasource.remote.ApiURL
import com.aldikitta.jetmvvmmovie.data.model.BaseModel
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.utils.network.DataState

@Composable
fun SearchUI(
    navController: NavController,
    searchData: MutableState<DataState<BaseModel>?>,
    itemClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(3),
            modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
            content = {
                searchData.value?.let {
                    if (it is DataState.Success<BaseModel>) {
                        items(items = it.data.results, itemContent = { item ->
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ApiURL.IMAGE_URL.plus(
                                        item.posterPath
                                    ),
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(180.dp)
                                    .padding(5.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .clickable {
                                        itemClick.invoke()
                                        navController.navigate(
                                            NavigationScreen.MovieDetail.MOVIE_DETAIL.plus(
                                                "/${item.id}"
                                            )
                                        )
                                    }
                            )

                        }
                        )
                    }
                }
            })
    }
}