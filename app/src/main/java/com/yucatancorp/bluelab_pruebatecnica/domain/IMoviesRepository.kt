package com.yucatancorp.bluelab_pruebatecnica.domain

interface IMoviesRepository {
    suspend fun doNetworkCall()
}