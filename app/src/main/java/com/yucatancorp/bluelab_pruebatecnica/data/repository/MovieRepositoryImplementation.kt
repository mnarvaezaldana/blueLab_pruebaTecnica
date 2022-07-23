package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.util.Log
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MoviesDatabase
import com.yucatancorp.bluelab_pruebatecnica.data.local.toMovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository

class MovieRepositoryImplementation(
    private val moviesApi: MoviesApi,
    private val db: MoviesDatabase,
    private val appContext: Application
): IMoviesRepository {

    private val dao = db.dao

    override suspend fun getTopRatedCall() {
        val response = moviesApi.getTopRatedCall()
        response.results.forEach { movie ->
            dao.insertMovie(movie.toMovieEntity())
        }
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        //response.results.forEach { movie -> Log.e("message", movie.overview) }
    }
}