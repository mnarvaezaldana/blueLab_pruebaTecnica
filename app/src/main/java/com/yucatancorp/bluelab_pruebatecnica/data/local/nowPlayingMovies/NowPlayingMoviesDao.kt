package com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface NowPlayingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlayingMoviesEntity: NowPlayingMoviesEntity)
}