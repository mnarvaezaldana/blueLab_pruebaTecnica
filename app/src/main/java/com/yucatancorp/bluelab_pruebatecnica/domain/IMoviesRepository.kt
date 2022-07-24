package com.yucatancorp.bluelab_pruebatecnica.domain

import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

interface IMoviesRepository {
    suspend fun performTasks()
    suspend fun getTopRatedCall()
    suspend fun getTopRatedQuery(): ArrayList<Movie>
    suspend fun getNowPlayingCall()
    suspend fun getNowPlayingQuery(): ArrayList<Movie>
}