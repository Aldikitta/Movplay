package com.aldikitta.jetmvvmmovie.ui.screens.genre

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.data.model.Genres
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.ui.components.HomeScreen
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.nowplaying.NowPlayingViewModel
import com.aldikitta.jetmvvmmovie.utils.network.DataState

@Composable
fun GenreTestingScreen(
    navController: NavController,
//    aldi: Genre
) {
    val genreTestingScreenViewModel = hiltViewModel<GenreTestingScreenViewModel>()
    val genres = genreTestingScreenViewModel.genres.value
    val itemsDrawer = listOf<Genre>()
    val genreName = remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        genreTestingScreenViewModel.genreListTestingScreen()
    }
    if (genres is DataState.Success<Genres>) {
        CallLazyCall(genres.data.genres, navController){
            genreName.value = it
        }
    }
}

@Composable
fun CallLazyCall(
    genres: List<Genre>,
    navController: NavController,
    closeDrawer: (genreName: String) -> Unit

) {
    LazyColumn() {
        items(items = genres) { item ->
            LazyCall(item = item, onItemClick = {
                navController.navigate(NavigationScreen.NAVIGATION_DRAWER.plus("/${it.id}")) {
                    launchSingleTop = true
                }
                closeDrawer(it.name)
            }
            )
        }
    }
}

@Composable
fun LazyCall(item: Genre, onItemClick: (Genre) -> Unit
) {
    Text(text = item.name, Modifier.clickable { onItemClick(item) })
}


