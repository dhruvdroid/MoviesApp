package com.androidarchitecture.data.entities

import com.androidarchitecture.domain.models.Actor
import com.androidarchitecture.domain.models.ActorWrapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//
// Created by  on 12/09/20.
//

@Serializable
data class CastListWrapperResponse(
    @SerialName("cast") val castResponse: List<CastResponse>,
    @SerialName("id") val id: Int
) {

    fun toActorWrapper() = ActorWrapper(
        id,
        castResponse.map { it.toActor() }
    )

}


@Serializable
data class CastResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String?
) {

    fun toActor() = Actor(
        id,
        if (profilePath.isNullOrEmpty())
            "https://media.comicbook.com/files/img/default-movie.png"
        else
            "http://image.tmdb.org/t/p/w92$profilePath",
        name
    )
}

