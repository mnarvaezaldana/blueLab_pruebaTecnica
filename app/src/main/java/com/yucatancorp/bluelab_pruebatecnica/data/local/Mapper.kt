package com.yucatancorp.bluelab_pruebatecnica.data.local

import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.GenresIds
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie

fun Movie.toMovieEntity(): MovieEntity {

    val genresIdsArrayList = genreIds.toCollection(ArrayList())

    return MovieEntity(
        isAnAdultFilm = isAnAdultFilm,
        genericFilmImageUrl = genericFilmImageUrl,
        genresIds = GenresIds(genresIdsArrayList),
        movieId = movieId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterFilmImageUrl = posterFilmImageUrl,
        releaseDate = releaseDate,
        customTitle = customTitle,
        isVideo = isVideo,
        averageVote = averageVote,
        voteCount = voteCount,
    )
}