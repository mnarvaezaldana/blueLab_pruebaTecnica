package com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TopRatedMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRated(topRatedMoviesEntity: TopRatedMoviesEntity)
}