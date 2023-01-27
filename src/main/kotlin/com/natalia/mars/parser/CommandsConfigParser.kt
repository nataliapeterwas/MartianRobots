package com.natalia.mars.parser

import com.natalia.mars.Command
import com.natalia.mars.MoveForwardCommand
import com.natalia.mars.MoveLeftCommand
import com.natalia.mars.MoveRightCommand

class CommandsConfigParser(
    private val moveForwardCommand: MoveForwardCommand,
    private val moveRightCommand: MoveRightCommand,
    private val moveLeftCommand: MoveLeftCommand) {
    fun parse(input: String): List<Command> {
        require(input.length < 101) { "Too long instruction" }
        require(input.toList().all { it.isLetter() && it.isUpperCase() }) { "Incorrect moves" }

        val splitMovesString = input.split("").toMutableList()
        splitMovesString.removeIf { it.isEmpty() }

        val splitMoves = splitMovesString.map {
            when (it) {
                "F" -> moveForwardCommand
                "R" -> moveRightCommand
                "L" -> moveLeftCommand
                else -> throw Exception("Incorrect move")
            }
        }

        return splitMoves.toList()
    }
}