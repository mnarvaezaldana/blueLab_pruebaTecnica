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
        response.results.forEach { movie -> Log.e("message", movie.customTitle) }
        Log.e("message", response.totalResults.toString())
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        Log.e("message", response.string())
    }
}