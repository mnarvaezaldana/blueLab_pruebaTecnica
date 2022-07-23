package com.yucatancorp.bluelab_pruebatecnica.data.local.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class MovieEntity(
    val isAnAdultFilm: Boolean,
    val genericFilmImageUrl: String,
    val genreIds: GenresIds,
    val movieId: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterFilmImageUrl: String,
    val releaseDate: String,
    val customTitle: String,
    val isVideo: Boolean,
    val averageVote: Double,
    val voteCount: Int,
    @PrimaryKey val id: Int? = null
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