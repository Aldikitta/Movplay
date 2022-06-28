package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aldikitta.jetmvvmmovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(repository: MovieRepository) : ViewModel() {
    val popularMovies = repository.popularPagingDataSource().cachedIn(viewModelScope)
}