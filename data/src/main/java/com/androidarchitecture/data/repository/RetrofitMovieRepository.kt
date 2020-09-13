package com.androidarchitecture.data.repository

import com.androidarchitecture.data.repository.base.BaseRestApiRepository
import com.androidarchitecture.data.retrofit.ApiService
import com.androidarchitecture.domain.models.ActorWrapper
import com.androidarchitecture.domain.models.MovieWrapper
import com.androidarchitecture.domain.models.Result
import com.androidarchitecture.domain.repository.MovieRepository
import javax.inject.Inject

class RetrofitMovieRepository @Inject constructor(
    private val api: ApiService
) : MovieRepository,
    BaseRestApiRepository() {

    override suspend fun getSimilarMovies(id: Int): Result<MovieWrapper> {
        return parseResult(api.getSimilarRecommendations(id)) { response -> response.toMovieWrapper() }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieWrapper> {
        return parseResult(api.getMovieDetails(id)) { response -> response.toMovieWrapper() }
    }

    override suspend fun getMovies(pageNum: Int): Result<MovieWrapper> {
        return parseResult(api.fetchMovies(pageNum)) { response -> response.toMovieWrapper() }
    }

    override suspend fun getCastDetails(id: Int): Result<ActorWrapper> {
        return parseResult(api.getCastDetails(id)) { it.toActorWrapper() }
    }

    override suspend fun searchMovie(movieQuery: String): Result<MovieWrapper> {
        return parseResult(api.searchMovie(movieQuery)) { it ->
            it.toFilter(movieQuery)
        }
    }
}
