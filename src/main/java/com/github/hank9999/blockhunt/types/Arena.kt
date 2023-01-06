package com.github.hank9999.blockhunt.types

import kotlinx.serialization.Serializable

@Serializable
data class Arena(
    val name: String,
    val startLoc: LocationCoordinate? = null,
    val endLoc: LocationCoordinate? = null,
    val minPlayers: Int? = null,
    val maxPlayers: Int? = null,
    val gameTime: Int? = null,
    val seekerAmount: Int? = null,
    val waitStartTime: Int? = null,
    val lobbyWarp: LocationCoordinate? = null,
    val seekerWarp: LocationCoordinate? = null,
    val spawnWarps: List<LocationCoordinate>? = null
)