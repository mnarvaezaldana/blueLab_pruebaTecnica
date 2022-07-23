package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies where movie_id LIKE :id")
     suspend fun searchMovie(id: Int): List<MovieEntity>
}