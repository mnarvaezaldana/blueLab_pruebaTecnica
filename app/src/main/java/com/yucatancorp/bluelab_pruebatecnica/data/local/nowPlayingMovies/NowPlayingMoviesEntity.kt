package com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIds
import com.yucatancorp.bluelab_pruebatecnica.data.models.DatesPlaying

@Entity(tableName = "NowPlaying", primaryKeys = ["id"])
data class NowPlayingMoviesEntity(
    @ColumnInfo(name = "dates") val dates: MDatesPlaying,
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "movies_id") val moviesId: MoviesIds?,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @ColumnInfo(name = "id") val id: Int? = null
)

data class MDatesPlaying(
    val maximum: String = String(),
    val minimum: String = String()
)

open class DatesPlayingConverter {

    @TypeConverter
    fun toDatesPlaying(value: String?): MDatesPlaying {
        if (value == null || value.isEmpty()) {
            return MDatesPlaying()
        }

        val list: List<String> = value.split(",")
        return MDatesPlaying(list.first(), list.last())
    }

    @TypeConverter
    fun toString(dates: MDatesPlaying?): String {

        var string = ""

        if (dates == null) {
            return string
        }

        string += dates.maximum
        string += dates.minimum

        return string
    }
}
