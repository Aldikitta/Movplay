package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aldikitta.jetmvvmmovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    fun moviesByGenre(genreId: String) =
        repo.genrePagingDataSource(genreId).cachedIn(viewModelScope)
}