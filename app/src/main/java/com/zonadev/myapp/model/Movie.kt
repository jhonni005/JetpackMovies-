package com.zonadev.myapp.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String,
    val genres: List<Genre>,
    val vote_average: Double
)

data class Genre(
    val id:Int,
    val name:String
)

