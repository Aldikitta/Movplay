package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldikitta.jetmvvmmovie.data.model.Genres
import com.aldikitta.jetmvvmmovie.data.repository.MovieRepository
import com.aldikitta.jetmvvmmovie.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreTestingScreenViewModel @Inject constructor(private val repo: MovieRepository) :
    ViewModel() {
    val genres: MutableState<DataState<Genres>?> = mutableStateOf(null)

    fun genreListTestingScreen() {
        viewModelScope.launch {
            repo.genreList().onEach {
                genres.value = it
            }.launchIn(viewModelScope)
        }
    }
}