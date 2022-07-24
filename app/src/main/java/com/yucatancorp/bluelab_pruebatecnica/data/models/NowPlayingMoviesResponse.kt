package com.yucatancorp.bluelab_pruebatecnica.data.models

import com.squareup.moshi.Json

data class NowPlayingMoviesResponse(
    @field:Json(name = "dates") val dates: DatesPlaying,
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: Array<Movie>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NowPlayingMoviesResponse

        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}

data class DatesPlaying(
    @field:Json(name = "maximun") val maximum: String = "",
    @field:Json(name = "minimun") val minimum: String = ""
)