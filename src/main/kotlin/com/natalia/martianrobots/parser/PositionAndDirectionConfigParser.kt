package com.natalia.martianrobots.parser

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.RobotPositionAndDirection

class PositionAndDirectionConfigParser(){
    fun parse(input: String): RobotPositionAndDirection {
        val robotPositionSplitAsChar = input.toList()
        val robotPositionSplitWithSpace = input.split(" ")

        require(
            robotPositionSplitWithSpace.size == 3 && robotPositionSplitAsChar.last().isLetter()
        ) { "Incorrect robot position" }

        val x = robotPositionSplitWithSpace[0].toInt()
        val y = robotPositionSplitWithSpace[1].toInt()
        val robotPosition = Position(x, y)

        val direction = Direction.values().firstOrNull { it.letter.toString() == robotPositionSplitWithSpace[2] }
            ?: throw Exception("Incorrect direction")

        return RobotPositionAndDirection(robotPosition, direction)
    }
}

