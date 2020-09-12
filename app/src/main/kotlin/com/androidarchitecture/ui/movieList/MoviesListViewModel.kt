package com.androidarchitecture.ui.movieList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.usecase.MovieListUseCase

class MoviesListViewModel @ViewModelInject constructor(
    private val movieListUseCase: MovieListUseCase
) : ViewModel() {
    var data: MutableLiveData<List<Movie>> = MutableLiveData()

    var pageNum = 0

    var shouldPaginate = false

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getMovies() {
        if (loading.value == true)
            return

        loading.value = true
        movieListUseCase
            .getMovies(++pageNum, this::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(wrapper: MovieWrapper) {
        data += wrapper.movies
        shouldPaginate = wrapper.isPaginationEnabled
        loading.value = false
    }

    private fun onApiFailure(error: Exception) {
        loading.value = false
        errorMessage.value = error.message
    }
}

operator fun<T> MutableLiveData<List<T>>.plusAssign(other: List<T>) {
    val temp = this.value ?: emptyList()
    this.value = mutableListOf<T>().apply {
        addAll(temp)
        addAll(other)
    }.toList()
}


