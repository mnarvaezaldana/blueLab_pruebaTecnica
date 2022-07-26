package com.yucatancorp.bluelab_pruebatecnica.domain

import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

interface IMoviesRepository {
    suspend fun performTasks()
    suspend fun getTopRatedCall(pageNumber: String = "1")
    suspend fun getTopRatedQuery(): ArrayList<Movie>
    suspend fun getNowPlayingCall(pageNumber: String = "1")
    suspend fun getNowPlayingQuery(): ArrayList<Movie>
    suspend fun getMovie(movieId: Int): Movie?
}