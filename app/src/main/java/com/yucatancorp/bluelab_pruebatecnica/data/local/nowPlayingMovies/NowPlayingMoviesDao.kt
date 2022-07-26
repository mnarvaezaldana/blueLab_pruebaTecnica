package com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIds

@Dao
interface NowPlayingMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlayingMoviesEntity: NowPlayingMoviesEntity)

    @Query("SELECT movies_id FROM nowPlaying")
    suspend fun getNowPlayingMoviesIds(): List<MoviesIds>
}