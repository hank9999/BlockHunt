package com.github.hank9999.blockhunt.commands

import com.github.hank9999.blockhunt.BlockHunt.Companion.plugin
import com.github.hank9999.blockhunt.enums.BasePath.Companion.arenaPath
import com.github.hank9999.blockhunt.managers.ArenaManager
import com.github.hank9999.blockhunt.utils.Config
import com.github.hank9999.blockhunt.utils.Utils.Companion.toColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import java.util.*
import java.util.stream.Collectors


class MainCommand : TabExecutor {
    override fun onCommand(commandSender: CommandSender, command: Command, s: String, strings: Array<String>): Boolean {
        if (!(command.name.equals("blockhunt", ignoreCase = true) || command.name.equals("bh", ignoreCase = true))) {
            return false
        }

        if (strings.isEmpty()) {
            commandSender.sendMessage("&3BlockHunt - 方块躲猫猫 ${plugin?.description?.version}\n&6hank9999 &f制作".toColor())
            return true
        }

        when (strings[0].lowercase()) {
            "reload" -> {
                if (!commandSender.hasPermission("blockhunt.admin")) {
                    commandSender.sendMessage("&3[BlockHunt] &4你没有权限执行此指令".toColor())
                    return false
                }
                Config.loadConfig()
                commandSender.sendMessage("&3[BlockHunt] &a配置已重载".toColor())
            }
            "newarena" -> {
                if (strings.size < 2) {
                    commandSender.sendMessage("&3[BlockHunt] &4缺少区域名称".toColor())
                    return false
                }
                val arenaName = strings[1]
                if (!arenaNamePattern.matches(arenaName)) {
                    commandSender.sendMessage("&3[BlockHunt] &4区域名称不合法, 只允许 英文字母(A-Z a-z), 下划线(_) 和 连字号(-)".toColor())
                    return false
                }
                ArenaManager(arenaName).save()
            }
            "listarena" -> {
                val arenaNames: MutableList<String> = mutableListOf()
                arenaPath.toFile().walk().maxDepth(1)
                    .filter { it.isFile && it.extension == "json" }
                    .forEach {
                        arenaNames.add(it.nameWithoutExtension)
                    }
                commandSender.sendMessage("&3[BlockHunt] &f目前有以下区域: ${arenaNames.joinToString()}".toColor())
            }
            "arena" -> {
                if (strings.size < 2) {
                    commandSender.sendMessage("&3[BlockHunt] &4缺少区域名称".toColor())
                    return false
                }
            }

        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        if (args.size <= 1) {
            return Arrays.stream(Commands).filter { s -> s.startsWith(args[0]) }.collect(Collectors.toList())
        }
        if (args.size == 2) {
            when (args[0].lowercase()) {
                "arena" -> {
                    val arenaNames: MutableList<String> = mutableListOf()
                    arenaPath.toFile().walk().maxDepth(1)
                        .filter { it.isFile && it.extension == "json" }
                        .forEach {
                            arenaNames.add(it.nameWithoutExtension)
                        }
                    return arenaNames.filter { it.startsWith(args[1]) }
                }
            }
        }
        return emptyList()
    }

    companion object {
        private val Commands = arrayOf("reload", "newarena", "listarena", "arena")
        private val arenaNamePattern = Regex("^[\\w-_]+\$")
    }
}