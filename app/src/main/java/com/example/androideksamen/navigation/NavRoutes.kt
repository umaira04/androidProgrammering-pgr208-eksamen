package com.example.androideksamen.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    object HomeRoute : NavRoutes()

    @Serializable
    object AnimeRoute : NavRoutes()

    @Serializable
    object AnimeSearchRoute : NavRoutes()

    @Serializable
    object AnimeIdeasRoute : NavRoutes()

    @Serializable
    object CharacterRoute : NavRoutes()

    @Serializable
    data class AnimeDetailsRoute(val animeId: Int) : NavRoutes()
}