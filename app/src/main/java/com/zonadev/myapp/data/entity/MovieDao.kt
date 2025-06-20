package com.zonadev.myapp.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zonadev.myapp.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)




}