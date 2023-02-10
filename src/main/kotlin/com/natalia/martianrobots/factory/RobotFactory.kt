package com.natalia.martianrobots.factory

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot

class RobotFactory {
    fun create(
        robotDirection: Direction,
        robotPosition: Position,
        isAlive: Boolean = true
    ) = Robot(robotDirection, robotPosition, isAlive)
}