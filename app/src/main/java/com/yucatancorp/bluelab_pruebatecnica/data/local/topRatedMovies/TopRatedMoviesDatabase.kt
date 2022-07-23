package com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TopRatedMoviesEntity::class],
    version = 1
)
@TypeConverters(MoviesIdsConverter::class)
abstract class TopRatedMoviesDatabase: RoomDatabase() {
    abstract val dao: TopRatedMoviesDao
}