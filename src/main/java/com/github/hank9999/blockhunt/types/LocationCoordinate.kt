package com.github.hank9999.blockhunt.types

import kotlinx.serialization.Serializable

@Serializable
data class LocationCoordinate(
    val x: Int,
    val y: Int,
    val z: Int
)
