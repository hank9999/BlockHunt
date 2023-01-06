package com.github.hank9999.blockhunt

import com.github.hank9999.blockhunt.utils.Config
import org.bukkit.plugin.java.JavaPlugin

class BlockHunt : JavaPlugin() {
    companion object {
        var plugin: BlockHunt? = null
    }

    override fun onEnable() {
        plugin = this
        Config.loadConfig()
    }
    override fun onDisable() {
        plugin = null
    }
}