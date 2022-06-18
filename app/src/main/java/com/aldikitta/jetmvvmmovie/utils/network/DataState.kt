package com.aldikitta.jetmvvmmovie.utils.network


sealed class DataState<out Response> {
    data class Success<out Success>(val data: Success): DataState<Success>()
    data class Error(val exception: Exception):DataState<Nothing>()
    object Loading : DataState<Nothing>()
}