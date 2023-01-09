package com.github.hank9999.blockhunt.utils

import com.github.hank9999.blockhunt.BlockHunt.Companion.plugin
class Logger {
    companion object {
        fun info(msg: String) {
            plugin?.logger?.info(msg)
        }

        fun info(msg: Any) {
            plugin?.logger?.info(msg.toString())
        }

        fun warning(msg: String) {
            plugin?.logger?.warning(msg)
        }

        fun warning(msg: Any) {
            plugin?.logger?.warning(msg.toString())
        }

        fun severe(msg: String) {
            plugin?.logger?.severe(msg)
        }

        fun severe(msg: Any) {
            plugin?.logger?.severe(msg.toString())
        }
    }
}