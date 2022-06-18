package com.aldikitta.jetmvvmmovie.ui.screens.mainscreen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aldikitta.jetmvvmmovie.utils.networkconnection.ConnectionState
import com.aldikitta.jetmvvmmovie.utils.networkconnection.connectivityState

@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainScreenViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember {
        mutableStateOf(true)
    }
    val searchProgressBar = remember {
        mutableStateOf(false)
    }
    val genreName = remember {
        mutableStateOf("")
    }
    //genre list for navigation drawer
    val genres = mainViewModel.genres.value
    //internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
}