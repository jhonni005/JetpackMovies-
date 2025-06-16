package com.zonadev.myapp.network

import com.zonadev.myapp.model.CastMember
import com.zonadev.myapp.model.CastResponse
import com.zonadev.myapp.model.Movie
import com.zonadev.myapp.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String,
        @Query("language") language:String = "es-ES",
        @Query("page") page: Int = 1
    ):MovieResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apikey: String,
        @Query("language") language:String = "es-ES",
        @Query("page") page: Int = 1
    ):MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") id: Int, // ✅ esto reemplaza {movie_id} en la URL
        @Query("api_key") apikey: String,
        @Query("language") language: String = "es-ES"
    ): Movie


    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCastMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): CastResponse

    @GET("search/movie")
    suspend fun  searchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
        @Query("query") query: String
    ): MovieResponse


}




/*https://api.themoviedb.org/3/movie/popular?api_key=TUK3Y&language=es-ES&page=1*/
/*Retrofit se encarga de convertir el JSON que devuelve la API en un objeto Kotlin: MovieResponse.
Y tú solo te encargas de trabajar con ese objeto: accediendo a la lista de películas con response.results.*/

/*
MovieResponse es como un contenedor de la respuesta completa
Cuando haces una petición a la API de TMDB, la respuesta
que te devuelve no es una lista directa de películas.
En realidad, TMDB te devuelve un objeto JSON completo
con varios campos*/