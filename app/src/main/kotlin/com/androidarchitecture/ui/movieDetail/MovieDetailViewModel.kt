package com.androidarchitecture.ui.movieDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.androidarchitecture.domain.models.Actor
import com.androidarchitecture.domain.models.ActorWrapper
import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.usecase.ActorListUseCase
import com.androidarchitecture.domain.usecase.SimilarMovieUseCase

class MovieDetailViewModel @ViewModelInject constructor(
    private val similarMovieUseCase: SimilarMovieUseCase,
    private val actorListUseCase: ActorListUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val data = savedStateHandle.get<Movie>("data") ?: -1

    val loading = MutableLiveData<Boolean>()
    val movieData = MutableLiveData<Movie>()
    val actorData = MutableLiveData<List<Actor>>()
    val similarMovies = MutableLiveData<List<Movie>>()
    val error = MutableLiveData<String>()

    // todo: move to zip api call
    fun getMovieDetails() {
        loading.value = true
//        movieDetailUseCase.getMovieDetail((data as Movie).id, ::onApiSuccess, ::onApiFailure)
    }

    // todo: move to zip api call
    fun getCastDetails() {
        loading.value = true
        actorListUseCase.getCaseDetails((data as Movie).id, ::onApiSuccess, ::onApiFailure)
    }

    // todo: move to zip api call
    fun getSimilarMovies() {
        loading.value = true
        similarMovieUseCase.getSimilarMovies(
            (data as Movie).id,
            ::onApiSuccess,
            ::onApiFailure
        )
    }

    private fun onApiSuccess(data: ActorWrapper) {
        loading.value = false
        actorData.value = data.movies
    }

    private fun onApiSuccess(data: MovieWrapper) {
        loading.value = false
        similarMovies.value = data.movies
    }


    private fun onApiFailure(exception: Exception) {
        loading.value = false
        error.value = exception.message
    }
}
