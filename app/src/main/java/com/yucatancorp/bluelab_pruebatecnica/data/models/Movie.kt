package com.yucatancorp.bluelab_pruebatecnica.data.models

import com.squareup.moshi.Json

data class Movie(
    @field:Json(name = "adult") val isAnAdultFilm: Boolean,
    @field:Json(name = "backdrop_path") val genericFilmImageUrl: String,
    @field:Json(name = "genre_ids") val genreIds: IntArray,
    @field:Json(name = "id") val movieId: Int,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterFilmImageUrl: String,
    @field:Json(name = "release_date") val releaseDate: String,
    @field:Json(name = "title") val customTitle: String,
    @field:Json(name = "video") val isVideo: Boolean,
    @field:Json(name = "vote_average") val averageVote: Double,
    @field:Json(name = "vote_count") val voteCount: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (!genreIds.contentEquals(other.genreIds)) return false

        return true
    }

    override fun hashCode(): Int {
        return genreIds.contentHashCode()
    }
}
