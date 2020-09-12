package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.repository.MovieRepository
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase<MovieWrapper>() {

    var pageNumber = 0

    override suspend fun makeRequest(): Result<MovieWrapper> {
        return repository.getMovies(pageNumber)
    }

    fun getMovies(
        pageNum: Int,
        success: (MovieWrapper) -> Unit,
        failure: (Exception) -> Unit
    ) {
        pageNumber = pageNum
        execute(success, failure)
    }
}
