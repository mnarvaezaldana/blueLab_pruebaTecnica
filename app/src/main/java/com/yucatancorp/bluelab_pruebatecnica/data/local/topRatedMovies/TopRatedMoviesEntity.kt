package com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies

import androidx.room.*

@Entity(tableName = "TopRated", primaryKeys = ["id"])
data class TopRatedMoviesEntity(
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "movies_id") val moviesId: MoviesIds?,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @ColumnInfo(name = "id") val id: Int? = null
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