package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.local.MoviesDatabase
import com.yucatancorp.bluelab_pruebatecnica.data.local.toMovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.toNowPlayingMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.toTopRatedMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository
import java.time.LocalDate

class MovieRepositoryImplementation(
    private val moviesApi: MoviesApi,
    private val db: MoviesDatabase,
    private val appContext: Application
): IMoviesRepository {

    private val moviesDao = db.daoMovies
    private val topRatedMoviesDao = db.topRatedMovies
    private val nowPlayingMoviesDao = db.nowPlayingMoviesDao

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun performTasks() {
        if(!isDateUpdated()) {
            getTopRatedCall()
            getNowPlayingCall()
        }
        getTopRatedQuery()
        getNowPlayingQuery()
    }

    override suspend fun getTopRatedCall() {
        val response = moviesApi.getTopRatedCall()
        topRatedMoviesDao.insertTopRated(response.toTopRatedMoviesEntity())
        response.results.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity())
        }
    }

    override suspend fun getTopRatedQuery() {
        topRatedMoviesDao.getTopRatedMoviesIds().moviesIds.forEach {
            //TODO - get Movie from ID and store in array
        }
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        nowPlayingMoviesDao.insertNowPlaying(response.toNowPlayingMoviesEntity())
    }

    override suspend fun getNowPlayingQuery() {
        nowPlayingMoviesDao.getNowPlayingMoviesIds().moviesIds.forEach {
            //TODO - get Movie from ID and store in array
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isDateUpdated(): Boolean {
        val sharedPref = appContext.getSharedPreferences(appContext.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPref.getBoolean(appContext.getString(R.string.is_date_updated_key), false)
    }

}