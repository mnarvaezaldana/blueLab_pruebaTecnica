package com.yucatancorp.bluelab_pruebatecnica.data.repository

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.yucatancorp.bluelab_pruebatecnica.R
import com.yucatancorp.bluelab_pruebatecnica.data.local.*
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIds
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.data.models.TopRatedMoviesResponse
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override suspend fun getTopRatedCall(pageNumber: String) {
        val response = moviesApi.getTopRatedCall(page = pageNumber)
        topRatedMoviesDao.insertTopRated(response.toTopRatedMoviesEntity())
        response.results.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity())
        }
    }

    override suspend fun getTopRatedQuery(): ArrayList<Movie> {
        val arrayOfMovies = arrayListOf<Movie>()
        topRatedMoviesDao.getTopRatedMoviesIds().forEach { moviesIds ->
            moviesIds.moviesIds.forEach { id ->
                getMovie(id)?.let { movieEntity -> arrayOfMovies.add(movieEntity) }
            }
        }
        return arrayOfMovies
    }

    override suspend fun getNowPlayingCall(pageNumber: String) {
        val response = moviesApi.getNowPlayingCall(page = pageNumber)
        nowPlayingMoviesDao.insertNowPlaying(response.toNowPlayingMoviesEntity())
        response.results.forEach { movie ->
            moviesDao.insertMovie(movie.toMovieEntity())
        }
    }

    override suspend fun getNowPlayingQuery(): ArrayList<Movie> {
        val arrayOfMovies = arrayListOf<Movie>()
        nowPlayingMoviesDao.getNowPlayingMoviesIds().forEach { moviesIds ->
            moviesIds.moviesIds.forEach { id ->
                getMovie(id)?.let { movieEntity -> arrayOfMovies.add(movieEntity) }
            }
        }
        return arrayOfMovies
    }

    override suspend fun getMovie(movieId: Int): Movie? {
        return moviesDao.searchMovie(movieId.toString(), "1")?.toMovie()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun isDateUpdated(): Boolean {
        val sharedPref = appContext.getSharedPreferences(appContext.getString(R.string.app_name), Context.MODE_PRIVATE)
        return sharedPref.getBoolean(appContext.getString(R.string.is_date_updated_key), false)
    }

}