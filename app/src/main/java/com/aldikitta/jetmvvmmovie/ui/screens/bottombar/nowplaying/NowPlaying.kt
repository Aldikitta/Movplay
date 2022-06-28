package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.ui.components.HomeScreen

@Composable
fun NowPlaying(
    navController: NavController
){
    val nowPlayingViewModel = hiltViewModel<NowPlayingViewModel>()
    HomeScreen(navController = navController, movies = nowPlayingViewModel.popularMovies)
}