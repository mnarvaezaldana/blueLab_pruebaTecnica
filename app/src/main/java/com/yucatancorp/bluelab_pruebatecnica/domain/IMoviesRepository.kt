package com.yucatancorp.bluelab_pruebatecnica.domain

interface IMoviesRepository {
    suspend fun getTopRatedCall()
    suspend fun getNowPlayingCall()
}