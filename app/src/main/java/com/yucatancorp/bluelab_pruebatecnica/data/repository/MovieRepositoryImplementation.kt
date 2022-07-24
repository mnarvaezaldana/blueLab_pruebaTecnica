package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.local.*
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
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

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun performTasks() {
        if(!isDateUpdated()) {
            getTopRatedCall()
            getNowPlayingCall()
        }
    }

    override suspend fun getTopRatedCall() {
        val response = moviesApi.getTopRatedCall()
        topRatedMoviesDao.insertTopRated(response.toTopRatedMoviesEntity())
        response.results.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity())
        }
    }

    override suspend fun getTopRatedQuery(): ArrayList<Movie> {
        val arrayOfMovies = arrayListOf<Movie>()
        topRatedMoviesDao.getTopRatedMoviesIds().moviesIds.forEach { id ->
            moviesDao.searchMovie(id.toString(), "1")?.let {
                    movieEntity -> arrayOfMovies.add(movieEntity.toMovie())
            }
        }
        return arrayOfMovies
    }

    override suspend fun getNowPlayingCall() {
        val response = moviesApi.getNowPlayingCall()
        nowPlayingMoviesDao.insertNowPlaying(response.toNowPlayingMoviesEntity())
    }

    override suspend fun getNowPlayingQuery(): ArrayList<Movie> {
        val arrayOfMovies = arrayListOf<Movie>()
        nowPlayingMoviesDao.getNowPlayingMoviesIds().moviesIds.forEach { id ->
            moviesDao.searchMovie(id.toString(), "1")?.let {
                    movieEntity -> arrayOfMovies.add(movieEntity.toMovie())
            }
        }
        return arrayOfMovies
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isDateUpdated(): Boolean {
        val sharedPref = appContext.getSharedPreferences(appContext.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPref.getBoolean(appContext.getString(R.string.is_date_updated_key), false)
    }

}