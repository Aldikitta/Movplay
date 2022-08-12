package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aldikitta.jetmvvmmovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    val popularMovies = movieRepository.nowPlayingPagingDataSource().cachedIn(viewModelScope)
}