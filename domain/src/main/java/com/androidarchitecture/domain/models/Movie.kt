package com.androidarchitecture.domain.models

import java.io.Serializable

//
// Created by Dhruv on 11/09/20.
//

data class Movie(
    var id: Int,
    var image: String,
    var overview: String,
    var title: String,
    var backdropImage: String,
) : Serializable

data class MovieWrapper(
    var movies: List<Movie>,
    var isPaginationEnabled : Boolean
) : Serializable