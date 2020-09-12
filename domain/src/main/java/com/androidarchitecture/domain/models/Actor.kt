package com.androidarchitecture.domain.models

import java.io.Serializable

//
// Created by  on 12/09/20.
//

data class Actor (
    var id: Int,
    var image: String,
    var name: String
) : Serializable

data class ActorWrapper(
    val id: Int,
    var movies: List<Actor>,
) : Serializable