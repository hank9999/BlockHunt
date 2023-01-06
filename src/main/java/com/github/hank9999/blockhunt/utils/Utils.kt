package com.github.hank9999.blockhunt.utils

import org.bukkit.ChatColor

class Utils {
    companion object {
        fun String.toColor(): String {
            return ChatColor.translateAlternateColorCodes('&', this)
        }
    }
}
