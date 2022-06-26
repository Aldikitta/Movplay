package com.aldikitta.jetmvvmmovie.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aldikitta.jetmvvmmovie.R
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)

        ) {
            searchData.value?.let {
                if (it is DataState.Success<BaseModel>) {
                    items(items = it.data.results, itemContent = { item ->
                        Row(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .clickable {
                                    itemClick.invoke()
                                    navController.navigate(
                                        NavigationScreen.MovieDetail.MOVIE_DETAIL.plus(
                                            "/${item.id}"
                                        )
                                    )
                                }
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ApiURL.IMAGE_URL.plus(
                                        item.backdropPath
                                    ),
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(80.dp)
//                                    .width(100.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            Column {
                                Text(
                                    text = item.title,
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = "${stringResource(R.string.rating_search)} ${item.voteAverage}",
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    )
                }
            }
        }
    }
}