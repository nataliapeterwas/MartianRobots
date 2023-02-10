package com.natalia.martianrobots.parser

import com.natalia.martianrobots.commands.Command
import com.natalia.martianrobots.commands.MoveForwardCommand
import com.natalia.martianrobots.commands.MoveLeftCommand
import com.natalia.martianrobots.commands.MoveRightCommand

class CommandsConfigParser(
    private val moveForwardCommand: MoveForwardCommand,
    private val moveRightCommand: MoveRightCommand,
    private val moveLeftCommand: MoveLeftCommand
) {
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