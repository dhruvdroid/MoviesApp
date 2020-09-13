package com.androidarchitecture.domain.repository

import com.androidarchitecture.domain.models.ActorWrapper
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.models.Result

interface MovieRepository {
    suspend fun getSimilarMovies(id: Int): Result<MovieWrapper>
    suspend fun getMovieDetail(id: Int): Result<MovieWrapper>
    suspend fun getMovies(pageNumber: Int): Result<MovieWrapper>
    suspend fun getCastDetails(id: Int): Result<ActorWrapper>
    suspend fun searchMovie(movieQuery: String): Result<MovieWrapper>
}
