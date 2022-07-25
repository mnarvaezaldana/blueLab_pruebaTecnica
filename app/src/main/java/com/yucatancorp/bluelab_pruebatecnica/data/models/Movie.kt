package com.yucatancorp.bluelab_pruebatecnica.data.models

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Movie(
    @field:Json(name = "adult") val isAnAdultFilm: Boolean,
    @field:Json(name = "backdrop_path") val genericFilmImageUrl: String?,
    @field:Json(name = "genre_ids") val genreIds: IntArray?,
    @field:Json(name = "id") val movieId: Int,
    @field:Json(name = "original_language") val originalLanguage: String?,
    @field:Json(name = "original_title") val originalTitle: String?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterFilmImageUrl: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "title") val customTitle: String?,
    @field:Json(name = "video") val isVideo: Boolean,
    @field:Json(name = "vote_average") val averageVote: Double,
    @field:Json(name = "vote_count") val voteCount: Int
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.createIntArray(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readInt()
    )

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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeBoolean(isAnAdultFilm)
        parcel?.writeString(genericFilmImageUrl)
        parcel?.writeIntArray(genreIds)
        parcel?.writeInt(movieId)
        parcel?.writeString(originalLanguage)
        parcel?.writeString(originalTitle)
        parcel?.writeString(overview)
        parcel?.writeDouble(popularity)
        parcel?.writeString(posterFilmImageUrl)
        parcel?.writeString(releaseDate)
        parcel?.writeString(customTitle)
        parcel?.writeBoolean(isVideo)
        parcel?.writeDouble(averageVote)
        parcel?.writeInt(voteCount)
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
