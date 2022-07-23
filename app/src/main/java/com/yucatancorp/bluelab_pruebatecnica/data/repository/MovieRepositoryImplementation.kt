package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.util.Log
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository

class MovieRepositoryImplementation(
    private val moviesApi: MoviesApi,
    private val appContext: Application
): IMoviesRepository {

    override suspend fun doNetworkCall() {
        val response = moviesApi.doNetworkCall()
        Log.e("message", response.string())
    }

}