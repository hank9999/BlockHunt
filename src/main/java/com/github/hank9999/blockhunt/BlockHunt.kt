package com.github.hank9999.blockhunt

import com.github.hank9999.blockhunt.utils.Config
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Paths
import com.github.hank9999.blockhunt.enums.BasePath

class BlockHunt : JavaPlugin() {
    companion object {
        var plugin: BlockHunt? = null
    }

    override fun onEnable() {
        plugin = this

        BasePath.arenaPath = Paths.get(dataFolder.path, BasePath.Arena.path)
        BasePath.arenaPath.toFile().mkdirs()

        Config.loadConfig()
    }
    override fun onDisable() {
        plugin = null
    }
}