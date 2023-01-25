package parser

import Command
import MoveForwardCommand
import MoveLeftCommand
import MoveRightCommand

class CommandsConfigParser {
    fun parse(input: String): List<Command> {
        require(input.length < 101) { "Too long instruction" }
        require(input.toList().all { it.isLetter() && it.isUpperCase() }) { "Incorrect moves" }

        val splitMovesString = input.split("").toMutableList()
        splitMovesString.removeIf { it.isEmpty() }

        val splitMoves = splitMovesString.map {
            when (it) {
                "F" -> MoveForwardCommand
                "R" -> MoveRightCommand
                "L" -> MoveLeftCommand
                else -> throw Exception("Incorrect move")
            }
        }

        return splitMoves.toList()
    }
}