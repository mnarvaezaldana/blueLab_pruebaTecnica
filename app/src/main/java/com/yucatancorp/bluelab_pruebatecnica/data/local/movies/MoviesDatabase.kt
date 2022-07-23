package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MovieEntity::class],
    version = 1
)
@TypeConverters(GenresIdsConverter::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract val dao: MovieDao
}