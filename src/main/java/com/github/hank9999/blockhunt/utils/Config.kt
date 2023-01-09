package com.github.hank9999.blockhunt.utils

import com.github.hank9999.blockhunt.BlockHunt.Companion.plugin

class Config {
    companion object {
        fun loadConfig() {
            plugin?.saveDefaultConfig()
            plugin?.reloadConfig()
            setValue()
        }

        fun setValue() {

        }
    }
}