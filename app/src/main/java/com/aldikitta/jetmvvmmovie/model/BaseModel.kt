package com.aldikitta.jetmvvmmovie.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)