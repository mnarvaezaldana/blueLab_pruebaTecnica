package com.yucatancorp.bluelab_pruebatecnica.data.remote

import com.yucatancorp.bluelab_pruebatecnica.data.models.TopRatedMovieResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("top_rated")
    suspend fun getTopRatedCall(
        @Query("api_key")api_key: String = API_KEY,
        @Query("language")language: String = "en-US",
        @Query("page")page: String = "1"
    ): TopRatedMovieResponse

    @GET("now_playing")
    suspend fun getNowPlayingCall(
        @Query("api_key")api_key: String = API_KEY,
        @Query("language")language: String = "en-US",
        @Query("page")page: String = "1"
    ): ResponseBody

    companion object {
        const val API_KEY = "fd2758f5c10e8a34ee4443f212aa86b0"
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
}