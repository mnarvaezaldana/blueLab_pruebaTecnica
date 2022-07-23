package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter

@Entity(tableName = "Movies", primaryKeys = ["movie_id"])
data class MovieEntity(
    @ColumnInfo(name = "is_an_adult_film") val isAnAdultFilm: Boolean = false,
    @ColumnInfo(name = "generic_film_image_url") val genericFilmImageUrl: String = "",
    @ColumnInfo(name = "genres_id") val genresIds: GenresIds = GenresIds(),
    @ColumnInfo(name = "movie_id") val movieId: Int = 0,
    @ColumnInfo(name = "original_language") val originalLanguage: String = "",
    @ColumnInfo(name = "original_title") val originalTitle: String = "",
    @ColumnInfo(name = "overview") val overview: String = "",
    @ColumnInfo(name = "popularity") val popularity: Double = 0.0,
    @ColumnInfo(name = "poster_film_image_url") val posterFilmImageUrl: String = "",
    @ColumnInfo(name = "release_date") val releaseDate: String = "",
    @ColumnInfo(name = "custom_title") val customTitle: String = "",
    @ColumnInfo(name = "is_video") val isVideo: Boolean = false,
    @ColumnInfo(name = "average_vote") val averageVote: Double = 0.0,
    @ColumnInfo(name = "vote_count") val voteCount: Int = 0,
)

data class GenresIds(
    val genreIds: ArrayList<Int> = ArrayList()
)

class GenresIdsConverter {

    @TypeConverter
    fun toMoviesIds(value: String?): GenresIds {
        if (value == null || value.isEmpty()) {
            return GenresIds()
        }

        val list: List<String> = value.split(",")
        val longList = ArrayList<Int>()
        for (item in list) {
            if (item.isNotEmpty()) {
                longList.add(item.toInt())
            }
        }
        return GenresIds(longList)
    }

    @TypeConverter
    fun toString(genreIds: GenresIds?): String {

        var string = ""

        if (genreIds == null) {
            return string
        }

        genreIds.genreIds.forEach {
                id -> string += "$id,"
        }
        return string
    }
}