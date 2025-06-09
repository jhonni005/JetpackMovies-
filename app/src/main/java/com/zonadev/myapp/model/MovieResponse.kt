package com.zonadev.myapp.model

import com.google.gson.annotations.SerializedName

class MovieResponse(
    val page: Int,
   @SerializedName("results") val movies: List<Movie>,
    val total_pages:Int,
    val total_results:Int
)