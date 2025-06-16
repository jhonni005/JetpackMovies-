package com.zonadev.myapp.data

import com.zonadev.myapp.model.CastMember
import com.zonadev.myapp.model.CastResponse
import com.zonadev.myapp.model.Movie


interface MovieRepository{
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getTrendingMovies():List<Movie>
    suspend fun getMovieDetailById(id:Int):Movie
    suspend fun getRecommendedMovies(id:Int):List<Movie>
    suspend fun getCastMovie(id:Int):List<CastMember>
    suspend fun searchMovies(query:String): List<Movie>
}

/*La interfaz te permite definir que operaciones puede hacer el repositorio*/
/*Define solo lo que puedo hacer*/