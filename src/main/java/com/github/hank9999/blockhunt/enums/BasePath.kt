package com.github.hank9999.blockhunt.enums

import java.nio.file.Path

enum class BasePath(val path: String) {
    Arena("arenas");

    companion object {
        lateinit var arenaPath: Path
    }
}