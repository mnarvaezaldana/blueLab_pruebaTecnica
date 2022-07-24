package com.yucatancorp.bluelab_pruebatecnica.data.local

import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.GenresIds
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MovieEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies.MDatesPlaying
import com.yucatancorp.bluelab_pruebatecnica.data.local.nowPlayingMovies.NowPlayingMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.MoviesIds
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesEntity
import com.yucatancorp.bluelab_pruebatecnica.data.models.DatesPlaying
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.data.models.NowPlayingMoviesResponse
import com.yucatancorp.bluelab_pruebatecnica.data.models.TopRatedMoviesResponse

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

fun TopRatedMoviesResponse.toTopRatedMoviesEntity(): TopRatedMoviesEntity {

    val moviesId = results.map { movie -> movie.movieId }.toCollection(ArrayList())

    return TopRatedMoviesEntity(
        page = page,
        moviesId = MoviesIds(moviesId),
        totalPages = totalPages,
        totalResults = totalResults
    )
}

fun NowPlayingMoviesResponse.toNowPlayingMoviesEntity(): NowPlayingMoviesEntity {

    val moviesId = results.map { movie -> movie.movieId }.toCollection(ArrayList())

    return NowPlayingMoviesEntity(
        dates = MDatesPlaying(dates.maximum, dates.minimum),
        page = page,
        moviesId = MoviesIds(moviesId),
        totalPages = totalPages,
        totalResults = totalResults
    )
}