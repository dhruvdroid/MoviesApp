package com.androidarchitecture.domain.usecase

import com.androidarchitecture.domain.base.UseCase
import com.androidarchitecture.domain.models.ActorWrapper
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.repository.MovieRepository
import javax.inject.Inject

class ActorListUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase<ActorWrapper>() {

    var movieId: Int = 0

    override suspend fun makeRequest(): Result<ActorWrapper> {
        return repository.getCastDetails(movieId)
    }

    fun getCaseDetails(
        id: Int,
        success: (ActorWrapper) -> Unit,
        failure: (Exception) -> Unit
    ) {
        movieId = id
        execute(success, failure)
    }
}
