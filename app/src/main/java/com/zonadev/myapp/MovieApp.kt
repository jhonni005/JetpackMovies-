package com.zonadev.myapp

import android.app.Application
import androidx.room.Room
import com.zonadev.myapp.data.entity.MovieDb

class MovieApp: Application() {
    lateinit var room: MovieDb
        private set

    override fun onCreate() {
        super.onCreate()
        room = Room.databaseBuilder(applicationContext, MovieDb::class.java, "moviedb"
        ).build()
    }
}