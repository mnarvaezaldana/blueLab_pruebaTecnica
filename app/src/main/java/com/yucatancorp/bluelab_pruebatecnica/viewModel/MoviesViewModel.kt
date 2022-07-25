package com.yucatancorp.bluelab_pruebatecnica.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yucatancorp.bluelab_pruebatecnica.data.models.Movie
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository
): ViewModel() {

    val topRatedIds = MutableLiveData<ArrayList<Movie>>()
    val nowPlayingIds = MutableLiveData<ArrayList<Movie>>()

    fun doNetworkCalls() {
        viewModelScope.launch {
            moviesRepository.performTasks()
            topRatedIds.postValue(moviesRepository.getTopRatedQuery())
            nowPlayingIds.postValue(moviesRepository.getNowPlayingQuery())
        }
    }

    suspend fun getMovieQuery(movieId: Int): Movie? {
        return moviesRepository.getMovie(movieId)
    }
}