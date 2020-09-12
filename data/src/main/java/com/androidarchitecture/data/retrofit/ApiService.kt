package com.androidarchitecture.data.retrofit

import com.androidarchitecture.data.entities.CastListWrapperResponse
import com.androidarchitecture.data.entities.MovieResponse
import com.androidarchitecture.data.entities.MovieWrapperResponse
import com.androidarchitecture.domain.models.MovieWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val GET_MOVIES = "movie/now_playing"
        const val GET_MOVIE = "movie/{movie_id}"
    }

    @GET(GET_MOVIES)
    suspend fun fetchMovies(
        @Query("page") pageNumber: Int
    ): Response<MovieWrapperResponse>

    @GET(GET_MOVIE)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<MovieWrapperResponse>

    @GET("$GET_MOVIE/credits")
    suspend fun getCastDetails(
        @Path("movie_id") movieId: Int
    ): Response<CastListWrapperResponse>

        @GET("$GET_MOVIE/similar")
    suspend fun getSimilarRecommendations(
        @Path("movie_id") movieId: Int
    ): Response<MovieWrapperResponse>
}
