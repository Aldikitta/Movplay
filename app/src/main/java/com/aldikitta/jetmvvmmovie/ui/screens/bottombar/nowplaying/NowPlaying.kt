package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.cachedIn
import com.aldikitta.jetmvvmmovie.data.repository.MovieRepository
import com.aldikitta.jetmvvmmovie.ui.components.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun NowPlaying(
    navController: NavController
){
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(navController = navController, movies = nowPlayingViewModel.popularMovies)
}