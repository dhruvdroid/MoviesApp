package com.androidarchitecture.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.usecase.SearchUseCase

//
// Created by Dhruv on 12/09/20.
//

class SearchFragmentViewModel @ViewModelInject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var data: MutableLiveData<List<Movie>> = MutableLiveData()
    var shouldPaginate = false
    private var errorMessage: MutableLiveData<String> = MutableLiveData()

    fun searchMovies(query: String) {
        if (loading.value == true)
            return
        loading.value = true
        searchUseCase.searchMovie(query, this::onApiSuccess, this::onApiFailure)
    }

    private fun onApiSuccess(wrapper: MovieWrapper) {
        data.value = wrapper.movies.toMutableList()
        shouldPaginate = wrapper.isPaginationEnabled
        loading.value = false
    }

    private fun onApiFailure(error: Exception) {
        loading.value = false
        errorMessage.value = error.message
    }
}