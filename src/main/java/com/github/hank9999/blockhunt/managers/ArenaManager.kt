package com.github.hank9999.blockhunt.managers

import com.github.hank9999.blockhunt.enums.BasePath
import com.github.hank9999.blockhunt.json.JSON.Companion.json
import com.github.hank9999.blockhunt.types.Arena
import com.github.hank9999.blockhunt.types.LocationCoordinate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File
import java.nio.file.Paths

class ArenaManager(name: String) {
    private val arenaFile: File = Paths.get(BasePath.arenaPath.toString(), "$name.json").toFile()
    val name: String = name
    var startLoc: LocationCoordinate? = null
    var endLoc: LocationCoordinate? = null
    var minPlayers: Int? = null
    var maxPlayers: Int? = null
    var gameTime: Int? = null
    var seekerAmount: Int? = null
    var waitStartTime: Int? = null
    var lobbyWarp: LocationCoordinate? = null
    var seekerWarp: LocationCoordinate? = null
    var spawnWarps: List<LocationCoordinate>? = null

    init {
        if (!arenaFile.exists()) {
            arenaFile.createNewFile()
        }
        load()
    }

    fun load() {
        val fileContext = arenaFile.readText()
        if (fileContext.isNotEmpty()) {
            val arena = json.decodeFromString<Arena>(fileContext)
            startLoc = arena.startLoc
            endLoc = arena.endLoc
            minPlayers = arena.minPlayers
            maxPlayers = arena.maxPlayers
            gameTime = arena.gameTime
            seekerAmount = arena.seekerAmount
            waitStartTime = arena.waitStartTime
            lobbyWarp = arena.lobbyWarp
            seekerWarp = arena.seekerWarp
            spawnWarps = arena.spawnWarps
        }
    }

    fun reload() {
        load()
    }

    fun save() {
        val arena = Arena(
            name, startLoc, endLoc, minPlayers, maxPlayers, gameTime,
            seekerAmount, waitStartTime, lobbyWarp, seekerWarp, spawnWarps
        )
        val fileContext = json.encodeToString(arena)
        arenaFile.writeText(fileContext)
    }

}