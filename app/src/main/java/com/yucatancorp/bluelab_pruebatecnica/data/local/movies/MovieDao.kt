package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(MovieEntity: MovieEntity)


}