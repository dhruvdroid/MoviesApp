package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.repository.MovieRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class SimilarMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieWrapper>() {

    private var id by Delegates.notNull<Int>()

    override suspend fun makeRequest() = repository.getSimilarMovies(id)

    fun getSimilarMovies(id: Int, success: (MovieWrapper) -> Unit, failure: (Exception) -> Unit) {
        this.id = id
        execute(success, failure)
    }
}
