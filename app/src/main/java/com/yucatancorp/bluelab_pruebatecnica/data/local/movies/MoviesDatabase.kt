package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIdsConverter
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesDao
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesEntity

@Database(
    entities = [MovieEntity::class, TopRatedMoviesEntity::class],
    version = 1
)
@TypeConverters(GenresIdsConverter::class, MoviesIdsConverter::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val daoMovies: MovieDao
    abstract val topRatedMovies: TopRatedMoviesDao
}