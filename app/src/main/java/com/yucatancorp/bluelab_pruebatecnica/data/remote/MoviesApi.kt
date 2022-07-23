package com.yucatancorp.bluelab_pruebatecnica.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface MoviesApi {

    @GET("")
    suspend fun doNetworkCall(): ResponseBody
}