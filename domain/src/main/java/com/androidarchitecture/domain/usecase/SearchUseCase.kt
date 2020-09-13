package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.repository.MovieRepository
import javax.inject.Inject
import kotlin.properties.Delegates

class SearchUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieWrapper>() {

    private var query by Delegates.notNull<String>()

    override suspend fun makeRequest() = repository.searchMovie(query)

    fun searchMovie(query: String, success: (MovieWrapper) -> Unit, failure: (Exception) -> Unit) {
        this.query = query
        execute(success, failure)
    }
}
