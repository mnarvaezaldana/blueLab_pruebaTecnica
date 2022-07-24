package com.yucatancorp.bluelab_pruebatecnica.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yucatancorp.bluelab_pruebatecnica.domain.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository
): ViewModel() {

    val ids = MutableLiveData<ArrayList<Int>>()

    fun doNetworkCalls() {
        viewModelScope.launch {
            moviesRepository.performTasks()
            ids.postValue(moviesRepository.getTopRatedQuery())
        }
    }
}