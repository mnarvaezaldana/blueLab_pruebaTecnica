package com.yucatancorp.bluelab_pruebatecnica.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.GenresIdsConverter
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MovieDao
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies.DatesPlayingConverter
import com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies.NowPlayingMoviesDao
import com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies.NowPlayingMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIdsConverter
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesDao
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesEntity

@Database(
    entities = [MovieEntity::class, TopRatedMoviesEntity::class, NowPlayingMoviesEntity::class],
    version = 1
)
@TypeConverters(GenresIdsConverter::class, MoviesIdsConverter::class, DatesPlayingConverter::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val daoMovies: MovieDao
    abstract val topRatedMovies: TopRatedMoviesDao
    abstract val nowPlayingMoviesDao: NowPlayingMoviesDao
}