package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.ui.components.HomeScreen

@Composable
fun GenreScreen(
    navController: NavController,
    genreId: String
) {
    val genreViewModel = hiltViewModel<GenreViewModel>()
    HomeScreen(
        navController = navController,
        movies = genreViewModel.moviesByGenre(genreId)
    )
}