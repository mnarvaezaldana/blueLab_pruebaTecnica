package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository

class MovieRepositoryImplementation(
    private val api: MoviesApi,
    private val appContext: Application
): IMoviesRepository {

    override suspend fun doNetworkCall() {
        TODO("Not yet implemented")
    }

}