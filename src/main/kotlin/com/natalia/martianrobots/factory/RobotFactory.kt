package com.natalia.martianrobots.factory

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot
import com.natalia.martianrobots.RobotStatus

class RobotFactory {
    fun create(
        robotDirection: Direction,
        robotPosition: Position,
        robotStatus: RobotStatus = RobotStatus.ALIVE
    ) = Robot(robotDirection, robotPosition, robotStatus)
}