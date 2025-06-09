package com.zonadev.myapp.data

import android.annotation.SuppressLint
import com.zonadev.myapp.BuildConfig
import com.zonadev.myapp.model.CastMember
import com.zonadev.myapp.model.Movie
import com.zonadev.myapp.network.ApiService
import com.zonadev.myapp.utils.Constants


class MovieRepositoryImpl(private val apiService: ApiService):MovieRepository {

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getPopularMovies(): List<Movie> {
      val response = apiService.getPopularMovies(apikey = Constants.API_KEY)
        return response.movies
    }

    override suspend fun getTrendingMovies(): List<Movie> {
       val response = apiService.getTrendingMovies(apikey = Constants.API_KEY)
        return response.movies
    }

    @SuppressLint("SuspiciousIndentation")
    override suspend fun getMovieDetailById(id:Int): Movie {
      val response = apiService.getMovieDetailById(apikey =Constants.API_KEY, id = id)
        return response
    }

    override suspend fun getRecommendedMovies(id: Int): List<Movie> {
        val response = apiService.getRecommendedMovies(apiKey =Constants.API_KEY,id = id)
        return response.movies
    }

    override suspend fun getCastMovie(id: Int): List<CastMember> {
       val response = apiService.getCastMovie(apiKey = Constants.API_KEY,id = id)
        return response.cast
    }


}