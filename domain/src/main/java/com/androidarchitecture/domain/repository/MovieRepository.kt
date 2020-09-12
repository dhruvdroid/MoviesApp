package com.androidarchitecture.domain.repository

import com.androidarchitecture.domain.models.*

interface MovieRepository {
    suspend fun getSimilarMovies(id: Int): Result<MovieWrapper>
    suspend fun getMovieDetail(id: Int): Result<MovieWrapper>
    suspend fun getMovies(pageNumber: Int): Result<MovieWrapper>
    suspend fun getCastDetails(id: Int): Result<ActorWrapper>
}
