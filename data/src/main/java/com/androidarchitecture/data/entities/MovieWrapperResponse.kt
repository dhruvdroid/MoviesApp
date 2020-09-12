package com.androidarchitecture.data.entities

import com.androidarchitecture.domain.models.Movie
import com.androidarchitecture.domain.models.MovieWrapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//
// Created by Dhruv on 12/09/20.
//
@Serializable
data class MovieWrapperResponse(
    // @SerialName("dates") val dates: DatesResponse,
    @SerialName("page") val pageNum: Int,
    @SerialName("results") val movieList: List<MovieResponse>,
    @SerialName("total_pages") val totalPage: Int,
    @SerialName("total_results") val totalResults: Int
) {
    fun toMovieWrapper() = MovieWrapper(
        movieList.map { it.toMovie() },
        pageNum != totalPage
    )
}

@Serializable
data class DatesResponse(
    @SerialName("maximum") val maximum: String,
    @SerialName("minimum") val minimum: String
)

@Serializable
data class MovieResponse(
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("title") val title: String,
    @SerialName("id") val id: Int,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("overview") val overview: String
) {

    fun toMovie() = Movie(
        id,
        if (posterPath.isNullOrEmpty())
            "https://media.comicbook.com/files/img/default-movie.png"
        else
            "http://image.tmdb.org/t/p/w500$posterPath",
        title,
        if (backdropPath.isNullOrEmpty())
            "https://media.comicbook.com/files/img/default-movie.png"
        else
            "http://image.tmdb.org/t/p/original$backdropPath"
    )
}
