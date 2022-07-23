package com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies

import androidx.room.*

@Entity
data class TopRatedMoviesEntity(
    val page: Int,
    val moviesId: MoviesIds?,
    val totalPages: Int,
    val totalResults: Int,
    @PrimaryKey val id: Int? = null
)

data class MoviesIds(
    val moviesIds: ArrayList<Int> = ArrayList()
)

class MoviesIdsConverter {

    @TypeConverter
    fun toMoviesIds(value: String?): MoviesIds {
        if (value == null || value.isEmpty()) {
            return MoviesIds()
        }

        val list: List<String> = value.split(",")
        val intList = ArrayList<Int>()
        for (item in list) {
            if (item.isNotEmpty()) {
                intList.add(item.toInt())
            }
        }
        return MoviesIds(intList)
    }

    @TypeConverter
    fun toString(moviesId: MoviesIds?): String {

        var string = ""

        if (moviesId == null) {
            return string
        }

        moviesId.moviesIds.forEach {
            id -> string += "$id,"
        }
        return string
    }
}