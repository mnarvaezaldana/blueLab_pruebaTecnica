package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import com.yucatancorp.bluelab_pruebatecnica.data.local.MoviesDatabase
import com.yucatancorp.bluelab_pruebatecnica.data.local.toMovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.toNowPlayingMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.toTopRatedMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository

class MovieRepositoryImplementation(
    private val moviesApi: MoviesApi,
    private val db: MoviesDatabase,
    private val appContext: Application
): IMoviesRepository {

    private val moviesDao = db.daoMovies
    private val topRatedMoviesDao = db.topRatedMovies
    private val nowPlayingMoviesDao = db.nowPlayingMoviesDao

    override suspend fun getTopRatedCall() {
        val response = moviesApi.getTopRatedCall()
        topRatedMoviesDao.insertTopRated(response.toTopRatedMoviesEntity())
        response.results.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity())
        }
        //val responseMovie = moviesDao.searchMovie(278)
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        nowPlayingMoviesDao.insertNowPlaying(response.toNowPlayingMoviesEntity())
    }
}