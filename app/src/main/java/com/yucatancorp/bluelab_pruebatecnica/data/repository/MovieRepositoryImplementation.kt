package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.util.Log
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository

class MovieRepositoryImplementation(
    private val moviesApi: MoviesApi,
    private val appContext: Application
): IMoviesRepository {

    override suspend fun getTopRatedCall() {
        val response = moviesApi.getTopRatedCall()
        Log.e("message", response.string())
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        Log.e("message", response.string())
    }
}