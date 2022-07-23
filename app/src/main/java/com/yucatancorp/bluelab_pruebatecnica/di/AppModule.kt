package com.yucatancorp.bluelab_pruebatecnica.di

import android.app.Application
import androidx.room.Room
import com.yucatancorp.bluelab_pruebatecnica.data.local.movies.MoviesDatabase
import com.yucatancorp.bluelab_pruebatecnica.data.local.topRatedMovies.TopRatedMoviesDatabase
import com.yucatancorp.bluelab_pruebatecnica.data.remote.MoviesApi
import com.yucatancorp.bluelab_pruebatecnica.data.repository.MovieRepositoryImplementation
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesApi: MoviesApi, db: MoviesDatabase, app: Application): IMoviesRepository {
        return MovieRepositoryImplementation(moviesApi, db, app)
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(app: Application): MoviesDatabase {
        return Room.databaseBuilder(
            app,
            MoviesDatabase::class.java,
            "moviesDb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTopRatedMoviesDatabase(app: Application): TopRatedMoviesDatabase {
        return Room.databaseBuilder(
            app,
            TopRatedMoviesDatabase::class.java,
            "topRatedMoviesDb.db"
        ).build()
    }
}