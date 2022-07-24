package com.yucatancorp.bluelab_pruebatecnica.domain

interface IMoviesRepository {
    suspend fun performTasks()
    suspend fun getTopRatedCall()
    suspend fun getTopRatedQuery()
    suspend fun getNowPlayingCall()
    suspend fun getNowPlayingQuery()
}