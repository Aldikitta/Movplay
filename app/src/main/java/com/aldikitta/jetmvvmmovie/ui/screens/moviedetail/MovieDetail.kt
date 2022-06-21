package com.aldikitta.jetmvvmmovie.ui.screens.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.aldikitta.jetmvvmmovie.R
import com.aldikitta.jetmvvmmovie.data.datasource.remote.ApiURL
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.MovieDetail
import com.aldikitta.jetmvvmmovie.ui.components.CircularIndeterminateProgressBar
import com.aldikitta.jetmvvmmovie.utils.network.DataState

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {
    val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
    val progressBar = remember {
        mutableStateOf(false)
    }
    val movieDetail = movieDetailViewModel.movieDetail
    val recommendedMovie = movieDetailViewModel.recommendedMovie
    val artist = movieDetailViewModel.artist

    LaunchedEffect(key1 = true) {
        movieDetailViewModel.movieDetailApi(movieId)
        movieDetailViewModel.recommendedMovieApi(movieId, page = 1)
        movieDetailViewModel.movieCredit(movieId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, verticalBias = 0.4f)
        movieDetail.value?.let { it ->
            if (it is DataState.Success<MovieDetail>) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.data.poster_path)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .height(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp)
                    ) {
                        Text(
                            text = it.data.title, modifier = Modifier.padding(top = 10.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp, top = 10.dp)
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = it.data.original_language,
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Text(
                                    text = stringResource(R.string.language),
                                    style = MaterialTheme.typography.titleLarge
                                )

                            }
                        }

                    }

                }
            }
        }

    }
}